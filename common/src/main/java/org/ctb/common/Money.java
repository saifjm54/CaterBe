package org.ctb.common;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Money {

    private BigDecimal amount;
    public Money(BigDecimal amount) {
        this.amount = amount;
    }
    public Money(String amount) {
        this.amount = new BigDecimal(amount);
    }
    public Money(int amount){
        this.amount = new BigDecimal(amount);
    }

    public String asString() {
        return amount.toPlainString();
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

    public Money add(Money other) {
        return new Money(amount.add(other.amount));
    }

    public Money multiply(int x) {
        return new Money(amount.multiply(new BigDecimal(x)));
    }

    public Long asLong() {
        return multiply(100).amount.longValue();
    }

}
