package main.org.dyson.dddstarter.domain.bankaccount;


import java.math.BigDecimal;

public class MoneyWithdrawnEvent {

    private final String accountId;
    private final BigDecimal amount;

    public MoneyWithdrawnEvent(String accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
