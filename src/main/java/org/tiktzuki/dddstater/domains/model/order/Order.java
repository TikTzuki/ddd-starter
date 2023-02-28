package org.tiktzuki.dddstater.domains.model.order;

import lombok.Getter;
import lombok.Setter;
import org.ddd.core.model.AggregateRoot;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ord")
@Getter
@Setter
public class Order extends AggregateRoot<Long> {

    private String shippingName;
    private String billingName;
    private Long nextFreeItemId;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
        super(0L); // FIXME
        // These setters are private and make sure the passed in parameters are valid:
//        setCustomer(customer.getId());
//        setShippingName(customer.getName());
//        setShippingAddress(customer.getAddress());
//        setBillingName(customer.getName());
//        setBillingAddress(customer.getAddress());
        nextFreeItemId = 1L;
        recalculateTotals();
    }

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