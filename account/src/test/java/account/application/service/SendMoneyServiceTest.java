package account.application.service;

import account.application.port.in.SendMoneyCommand;
import account.domain.Account;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SendMoneyServiceTest {

    @Test
    void transactionSucceeds() {
        Account sourceAccount = givenSourceAccount();
        Account targetAccount = givenTargetAccount();

        givenWithdrawalWillSuceed(sourceAccount);
        givenDepositWillSucceed(targetAccount);

        Money money = Money.of(500L);

        SendMoneyCommand command = new SendMoneyCommand(sourceAccount.getAccountId(), targetAccount.getAccountId(), money);

        boolean success = sendMoneyService.sendMoney(command);

        assertThat(success).isTrue();

        AccountId sourceAccountId = sourceAccount.getId();
        AccountId targetAccountId = targetAccount.getId();

        then(accountLock).should().lockAccount(eq(sourceAccount));
        then(targetAccount).should().deposit(eq(money), eq(sourceAccount));
        then(accountLock).should().releaseAccount(eq(sourceAccount));
    }
}
