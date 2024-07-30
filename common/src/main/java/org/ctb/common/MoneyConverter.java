package org.ctb.common;

import jakarta.persistence.AttributeConverter;

public class MoneyConverter implements AttributeConverter<Money,String> {

    @Override
    public String convertToDatabaseColumn(Money money) {
        return money == null ? null : money.asString();
    }

    @Override
    public Money convertToEntityAttribute(String s) {
        if (s == null) return null;
        return new Money(s);
    }

}
