package account.application.port.out;

import account.domain.Account;

import java.time.LocalDateTime;

public interface LoadAccountPort {
    public Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
