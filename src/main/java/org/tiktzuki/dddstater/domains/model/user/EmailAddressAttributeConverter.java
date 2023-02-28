package org.tiktzuki.dddstater.domains.model.user;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmailAddressAttributeConverter implements AttributeConverter<EmailAddress, String> {
    @Override
//    @Contract("null -> null")
    public String convertToDatabaseColumn(EmailAddress attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
//    @Contract("null -> null")
    public EmailAddress convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new EmailAddress(dbData);
    }

}