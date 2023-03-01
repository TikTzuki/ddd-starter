package org.tiktzuki.dddstater.domains.order.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter
public class PriceAttributeConverter implements AttributeConverter<Price, BigDecimal> {
    @Override
    public BigDecimal convertToDatabaseColumn(Price attribute) {
        return attribute == null ? null : attribute.getPrice();
    }

    @Override
    public Price convertToEntityAttribute(BigDecimal dbData) {
        return dbData == null ? null : new Price(dbData);
    }
}
