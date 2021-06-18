package me.rn00n.oauth2.jwt.infra.config;

import me.rn00n.oauth2.jwt.infra.commons.AppProperties;
import me.rn00n.oauth2.jwt.infra.commons.BaseControllerTest;
import me.rn00n.oauth2.jwt.models.account.Account;
import me.rn00n.oauth2.jwt.models.account.AccountRepository;
import me.rn00n.oauth2.jwt.models.account.AccountRole;
import me.rn00n.oauth2.jwt.models.account.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
class AuthServerConfigTest extends BaseControllerTest {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AppProperties appProperties;

    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    @DisplayName("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {
        // Given
        String username = appProperties.getUserUsername();
        String password = appProperties.getUserPassword();

        Account account = Account.builder()
                .email(username)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();

        accountService.saveAccount(account);

        mockMvc.perform(
                post("/oauth/token")
                        .with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
                        .param("username", username)
                        .param("password", password)
                        .param("grant_type", "password")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
        ;
    }
}