package main.org.dyson.dddstarter.domain.bankaccount;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.math.BigDecimal;

@Aggregate
@Slf4j
public class BankAccount {

    @AggregateIdentifier
    private String accountId;
    private BigDecimal balance;
    @CommandHandler
    public BankAccount(CreateAccountCommand cmd) {
        log.info("constructor bank account");
        AggregateLifecycle.apply(new AccountCreatedEvent(cmd.getAccountId(), cmd.getInitialBalance()));
    }

    public void deposit(BigDecimal amount) {
        AggregateLifecycle.apply(new MoneyDepositedEvent(accountId, amount));
    }

    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        AggregateLifecycle.apply(new MoneyWithdrawnEvent(accountId, amount));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        log.info("on account created event");
        this.accountId = event.getAccountId();
        this.balance = event.getInitialBalance();
    }

    @EventSourcingHandler
    public void on(MoneyDepositedEvent event) {
        log.info("new event {}", event);
        this.balance = balance.add(event.getAmount());
    }

    @EventSourcingHandler
    public void on(MoneyWithdrawnEvent event) {
        this.balance = balance.subtract(event.getAmount());
    }
}



