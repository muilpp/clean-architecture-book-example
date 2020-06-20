package account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    @Getter
    private AccountId accountId;
    private Money baselineBalance;
    @Getter
    private ActivityWindow activityWindow;

    public static Account withoutId(Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(null, baselineBalance, activityWindow);
    }

    public static Account withId(AccountId accountId, Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    public Money calculateBalance() {
        return Mondey.add(this.baselineBalance, this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money, AccountId targetAccountId) {
        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = new Activity(this.id, targetAccountId, LocalTime.now(), money);
        this.activityWindow.addActivity(withdrawal);

        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(this.calculateBalance(), money.negate()).isPositive();
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity deposit = new Activity(this.id, sourceAccountId, LocalDateTime.now(), money);
        this.activityWindow.addActivity(deposit);

        return true;
    }
}
