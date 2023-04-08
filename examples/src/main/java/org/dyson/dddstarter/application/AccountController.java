package main.org.dyson.dddstarter.application;

import main.org.dyson.dddstarter.domain.bankaccount.BankAccount;
import main.org.dyson.dddstarter.domain.bankaccount.CreateAccountCommand;
import main.org.dyson.dddstarter.domain.bankaccount.FindAllBankAccountQuery;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class AccountController {
    @Autowired
    CommandGateway commandGateway;

    @Autowired
    QueryGateway queryGateway;

    @PostMapping("/accounts")
    public CompletableFuture<Void> create(){
        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), BigDecimal.ONE));
    }
    @GetMapping("/accounts")
    public CompletableFuture<List<BankAccount>> find(){
        return queryGateway.query(new FindAllBankAccountQuery(), ResponseTypes.multipleInstancesOf(BankAccount.class));
    }

    @QueryHandler
    public List<BankAccount> handle(FindAllBankAccountQuery query) {
        return new ArrayList<>();
    }
}
