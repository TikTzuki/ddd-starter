package vn.unicloud.fnb.domain.order;

import jakarta.persistence.*;
import lombok.*;
import org.dyson.core.model.LocalEntity;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderItem extends LocalEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long productId;
    @ManyToOne
    private Order order;
    private String description;
    private Integer quantity;
    @Convert(converter = PriceAttributeConverter.class)
    private Price price;
    @Convert(converter = PriceAttributeConverter.class)
    private Price subTotal;

    OrderItem(Order order) {
        this.order = Objects.requireNonNull(order);
        this.quantity = 0;
        this.price = new Price(BigDecimal.ZERO);
        recalculateSubTotal();
    }


    private void recalculateSubTotal() {
        Price oldSubTotal = this.subTotal;
        this.subTotal = price.multiply(quantity);
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

    public void setPrice(Price price) {
        Objects.requireNonNull(price, "price must not be null");
        this.price = price;
        recalculateSubTotal();
    }

}