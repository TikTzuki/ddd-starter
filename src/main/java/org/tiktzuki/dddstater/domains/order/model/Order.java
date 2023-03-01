package org.tiktzuki.dddstater.domains.order.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;
import org.tiktzuki.dddstater.domains.product.model.Product;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ord")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class Order extends AggregateRoot<Long> {

    @Id
    @SequenceGenerator(name = Sequences.ORDER, sequenceName = Sequences.ORDER, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.ORDER)
    private Long id;

    private String shippingName;
    private String billingName;
    @Convert(converter = PriceAttributeConverter.class)
    private Price total;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    public void recalculateTotals() {
        this.total = items.stream().map(OrderItem::getSubTotal).reduce(Price.ZERO, Price::add);
        log.info("--> calculated: " + this.total.getPrice());
    }

    public OrderItem addItem(Product product) {
        OrderItem item = new OrderItem(this);
        item.setProductId(product.getId());
        item.setDescription(product.getName());
        this.items.add(item);
        return item;
    }

    public void ship() {
        log.info("--> prepare ship");
        registerEvent(new OrderShipped(this.getId(), Instant.now()));
        log.info("--> shipped");
    }
}