package main.org.dyson.dddstarter.domain.bankaccount;

import java.math.BigDecimal;

public class AccountCreatedEvent {

    private final String accountId;
    private final BigDecimal initialBalance;

    public AccountCreatedEvent(String accountId, BigDecimal initialBalance) {
        this.accountId = accountId;
        this.initialBalance = initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }
}

