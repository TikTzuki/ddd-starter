package vn.unicloud.fnb.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.dyson.core.model.AggregateRoot;
import vn.unicloud.fnb.domain.product.model.Product;
import vn.unicloud.fnb.dto.CreateOrderCommand;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "torder")
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
@org.axonframework.modelling.command.AggregateRoot
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
    private List<OrderItem> items = new ArrayList<>();

    @CommandHandler
    public Order(CreateOrderCommand createOrderCommand, OrderRepository orderRepository) {
        log.debug(this.toString());
        ship();
        orderRepository.save(this);
    }

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
        AggregateLifecycle.apply(new OrderShipped(this.id, Instant.now().getEpochSecond()));
        log.info("--> shipped");
    }
}