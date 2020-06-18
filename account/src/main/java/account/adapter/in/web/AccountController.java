package account.adapter.in.web;

import account.application.port.in.GetAccountBalanceQuery;
import account.application.port.in.SendMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final GetAccountBalanceQuery getAccountBalanceQuery;
    private final ListAccountsQuery listAccountsQuery;
    private final LoadAccountQuery loadAccountQuery;

    private final SendMoneyUseCase sendMoneyUseCase;
    private final CreateAccountUseCase createAccountUseCase;

    @GetMapping("/accounts")
    List listAccounts() {
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/accounds/id")
    AccountResource getAccount(@PathVariable("accountId") Long accountId) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @PostMapping("/accounts")
    AccountResource createAccount(@RequestBody AccountResource account) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
