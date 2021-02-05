package me.rn00n.server.infra.config;

import me.rn00n.server.infra.commons.BaseControllerTest;
import me.rn00n.server.models.system.account.dto.AccountSignUp;
import me.rn00n.server.models.system.account.repository.AccountRepository;
import me.rn00n.server.models.system.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    @DisplayName("access_token 발급 테스트")
    void getAuthToken() throws Exception {
        String username = "admin";
        String password = "1234";

        AccountSignUp accountSignUp = AccountSignUp.builder()
                .username("admin")
                .password("1234")
                .name("관리자")
                .phoneNo("010-0000-0000")
                .email("admin@mail.com")
                .build();
        accountService.saveAccount(accountSignUp);

        mockMvc.perform(post("/oauth/token")
//                .header("Authorization", "Basic YXBwOjEyMzQ=")
                .with(httpBasic("motooApp", "1234"))
                .param("username", "admin")
                .param("password", "1234")
                .param("grant_type", "password")
                .param("scope", "app")
        )
                .andDo(print())
                .andExpect(status().isOk())

        ;
    }
}