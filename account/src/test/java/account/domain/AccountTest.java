package account.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class AccountTest {

    @Test
    void withdrawalSucceeds() {
        AccountId accountId = new AccountId(1L);
        Account account = defaultAccount().withAccountId(accountId).withBaselineBalance(Money.of(555L)).build;

        boolean success = account.withdraw(Money.of(555L), new AccountId(99L));
        assertThat(success).isTrue();
        assertThat(account.calculateBalance().isEqualTo(Money.of(1000L)));
    }
}
