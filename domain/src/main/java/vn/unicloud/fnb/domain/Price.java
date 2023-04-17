package vn.unicloud.fnb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dyson.core.model.ValueObject;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Price implements ValueObject {
    private final BigDecimal value;

    public static Price valueOf(BigDecimal value) {
        return new Price(value);
    }

    public static Price valueOf(String value) {
        return new Price(new BigDecimal(value));
    }
}
