package org.tiktzuki.dddstater.domains.order.model;

import lombok.*;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;

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
public class Order extends AggregateRoot<Long> {

    @Id
    @SequenceGenerator(name = Sequences.ORDER, sequenceName = Sequences.ORDER, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.ORDER)
    private Long id;

    private String shippingName;
    private String billingName;
    private Long nextFreeItemId;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();


    private Long getNextFreeItemId() {
        return nextFreeItemId++;
    }

    void recalculateTotals() { // Package visibility to make the method accessible from OrderItem
//        this.total = items.stream().map(OrderItem::getSubTotal).reduce(Money.ZERO, Money::add);
    }

//    public OrderItem addItem(Product product) {
//        OrderItem item = new OrderItem(getNextFreeItemId(), this);
//        item.setProductId(product.getId());
//        item.setDescription(product.getName());
//        this.items.add(item);
//        return item;
//    }

    public void ship() {

        registerEvent(new OrderShipped(this.getId(), Instant.now()));
    }
}