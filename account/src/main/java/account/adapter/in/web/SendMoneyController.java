package account.adapter.in.web;

import account.application.port.in.SendMoneyCommand;
import account.application.port.in.SendMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping("/accounts/send/{sourceAccountId}")
    void sendMoney(@PathVariable("sourceAccountId") Long sourceAccountId) {
        SendMoneyCommand sendMoneyCommand = new SendMoneyCommand(new AccountId(sourceAccountId), null, null);
        
        sendMoneyUseCase,sendMoney(sendMoneyCommand);
    }
}
