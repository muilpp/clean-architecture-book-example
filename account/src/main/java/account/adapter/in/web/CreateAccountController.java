package account.adapter.in.web;

import account.application.port.in.GetAccountBalanceQuery;
import account.application.port.in.SendMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CreateAccountController {

    private final CreateAccountUseCase createAccountUseCase;

    @PostMapping("/accounts")
    AccountResource createAccount(@RequestBody AccountResource account) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
