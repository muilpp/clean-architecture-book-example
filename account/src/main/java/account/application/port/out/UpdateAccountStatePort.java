package account.application.port.out;

import account.domain.Account;

public interface UpdateAccountStatePort {
    public void updateActivities(Account account);
}
