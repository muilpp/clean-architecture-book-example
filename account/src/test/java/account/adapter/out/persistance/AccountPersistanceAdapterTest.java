package account.adapter.out.persistance;

import account.adapter.out.persistence.AccountPersistanceAdapter;
import account.adapter.out.persistence.ActivityRepository;
import account.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({AccountPersistanceAdapter.class, AccountMapper.class})
public class AccountPersistanceAdapterTest {

    @Autowired
    private AccountPersistanceAdapter adapterUnderTest;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    @Sql("AccountPersistanceAdapterTest.sql")
    void loadsAccount() {
        Account account = adapterUnderTest.loadAccount(new Account(1L), LocalDateTime.of(2018, 8, 10, 0, 0));

        assertThat(account.getActivityWindow().getActivities()).hasSize(2);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(500));
    }

}
