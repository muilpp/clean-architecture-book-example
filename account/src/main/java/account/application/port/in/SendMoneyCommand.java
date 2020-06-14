package account.application.port.in;

import account.application.port.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

    @NotNull(message = "Source account cannot be null")
    private final AccountId sourceAccountId;
    @NotNull(message = "Target account cannot be null")
    private final AccountId targetAccountId;
    @NotNull(message = "Money cannot be null")
    @Min(value = 0, message = "Money should not be less than 0")
    private final Money money;

    public SendMoneyCommand(AccountId sourceAccountId, AccountId targetAccountId, Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;

        this.validateSelf();
    }
}