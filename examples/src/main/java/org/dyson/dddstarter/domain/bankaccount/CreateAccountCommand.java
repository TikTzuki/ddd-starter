package main.org.dyson.dddstarter.domain.bankaccount;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class CreateAccountCommand {
    private final String accountId;
    private final BigDecimal initialBalance;
}
