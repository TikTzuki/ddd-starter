package org.tiktzuki.dddstater.domains.model.user;

import org.ddd.core.model.ValueObject;

public class EmailAddress implements ValueObject {
    private final String email;

    public EmailAddress(String email) {
        this.email = validate(email);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static String validate(String email) {
        if (!isValid(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        return email;
    }

    public static boolean isValid(String email) {
        // Validate the input string, return true or false depending on whether it is a valid e-mail address or not
        return true;
    }

}
