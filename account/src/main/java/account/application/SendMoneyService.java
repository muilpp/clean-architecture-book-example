package account.application;

import account.application.port.in.SendMoneyUseCase;
import account.application.port.out.LoadAccountPort;
import account.application.port.out.UpdateAccountStatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUseCase {
    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand sendMoneyCommand) {
        //TODO Implement!
        return false;
    }
}
