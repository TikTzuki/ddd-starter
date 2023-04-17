package vn.unicloud.fnb.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.dyson.core.model.ValueObject;

import java.math.BigDecimal;


@Getter
@RequiredArgsConstructor
public class Price implements ValueObject {
    public static final Price ZERO = new Price(BigDecimal.ZERO);
    private final BigDecimal price;


    Price multiply(Price v) {
        return new Price(this.price.multiply(v.price));
    }

    Price multiply(int v) {
        return new Price(this.price.multiply(BigDecimal.valueOf(v)));
    }

    Price add(Price v) {
        return new Price(this.price.add(v.price));
    }

    public static Price valueOf(int v) {
        return new Price(BigDecimal.valueOf(v));
    }

    public static BigDecimal validate(BigDecimal price) {
        if (!isValid(price)) {
            throw new IllegalArgumentException("Invalid email: " + price);
        }
        return price;
    }

    public static boolean isValid(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }
}
