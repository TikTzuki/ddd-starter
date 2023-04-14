package vn.unicloud.fnb.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dyson.core.model.AggregateRoot;
import vn.unicloud.fnb.product.model.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="torder")
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
        apply(new OrderShipped(null, null));
        log.info("--> shipped");
    }
}