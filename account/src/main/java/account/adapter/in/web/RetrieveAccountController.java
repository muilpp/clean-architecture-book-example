package account.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RetrieveAccountController {

    @GetMapping("/accounts")
    List listAccounts() {
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/accounds/id")
    AccountResource getAccount(@PathVariable("accountId") Long accountId) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
