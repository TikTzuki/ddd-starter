package org.tiktzuki.dddstater.domains.model.order;

import org.ddd.core.model.LocalEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class OrderItem extends LocalEntity<Long> {
    @ManyToOne
    private Order order;
    private String description;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subTotal;

    OrderItem(Long id, Order order) {
        super(id);
        this.order = Objects.requireNonNull(order);
        this.quantity = 0;
        this.price = BigDecimal.ZERO;
        recalculateSubTotal();
    }

    public OrderItem() {
        super(0L); // Fixme
    }

    private void recalculateSubTotal() {
        BigDecimal oldSubTotal = this.subTotal;
        this.subTotal = price.multiply(BigDecimal.valueOf(quantity));
        if (oldSubTotal != null && !oldSubTotal.equals(this.subTotal)) {
            this.order.recalculateTotals();
        }
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
        recalculateSubTotal();
    }

    public void setPrice(BigDecimal price) {
        Objects.requireNonNull(price, "price must not be null");
        this.price = price;
        recalculateSubTotal();
    }

}