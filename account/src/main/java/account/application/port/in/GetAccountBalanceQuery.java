package account.application.port.in;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);
}
