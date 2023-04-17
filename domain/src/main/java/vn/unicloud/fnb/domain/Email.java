package vn.unicloud.fnb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dyson.core.model.ValueObject;

@AllArgsConstructor
@Getter
public class Email implements ValueObject {
    private final String email;

    public static Email valueOf(String email) {
        return new Email(email);
    }
}
