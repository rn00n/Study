package me.rn00n.oauth.jwt.infra.config;

import me.rn00n.oauth.jwt.infra.commons.BaseControllerTest;
import me.rn00n.oauth.jwt.models.system.account.AccountRole;
import me.rn00n.oauth.jwt.models.system.account.domain.Account;
import me.rn00n.oauth.jwt.models.system.account.repository.AccountRepository;
import me.rn00n.oauth.jwt.models.system.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
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

        Account account = Account.builder()
                .username(username)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();
        accountService.saveAccount(account);

        mockMvc.perform(post("/oauth/token")
                .with(httpBasic("myApp", "1234"))
                .param("username", "admin")
                .param("password", "1234")
                .param("grant_type", "password")
        )
                .andDo(print())
                .andExpect(status().isOk())

        ;
    }
}