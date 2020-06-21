package account.adapter.in.web;

import account.application.port.in.SendMoneyCommand;
import account.application.port.in.SendMoneyUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SendMoneyController.class)
public class SendMoneyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SendMoneyUseCase sendMoneyUseCase;

    @Test
    void testSendMoney() throws Exception {
        mockMvc.perform(post("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}"), 41L, 42L, 500)
                .header("Content-type", "application/json")
                .andExpect(status().isOk());

        then(sendMoneyUseCase).should()
                .sendMoney(eq(new SendMoneyCommand(new AccountId(41L), new AccountId(42L), Money.of(500L))));
    }
}
