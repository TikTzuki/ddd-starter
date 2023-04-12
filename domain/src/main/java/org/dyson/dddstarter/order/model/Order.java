package org.dyson.dddstarter.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.dyson.core.model.AggregateRoot;
import org.dyson.dddstarter.product.model.Product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ord")
@Slf4j
@Aggregate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @AggregateIdentifier
    private Long id;

    private String shippingName;
    private String billingName;
    @Convert(converter = PriceAttributeConverter.class)
    private Price total;

    @OneToMany(mappedBy = "order")
    @AggregateMember
    private List<OrderItem> items = new ArrayList<>();

//    @CommandHandler
//    public Order(CreateOrderCommand command, OrderRepository orderRepository) {
//        log.debug("---> new order by command");
//        orderRepository.save(this);
//        ship();
//    }

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
        apply(new OrderShipped(this.getId(), Instant.now().getEpochSecond()));
//        apply(new OrderShipped(this.getId(), Instant.now().getEpochSecond()));
        log.info("--> shipped");
    }
}