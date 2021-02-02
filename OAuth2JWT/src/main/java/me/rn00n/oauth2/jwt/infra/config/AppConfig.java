package me.rn00n.oauth2.jwt.infra.config;

import me.rn00n.oauth2.jwt.infra.commons.AppProperties;
import me.rn00n.oauth2.jwt.models.account.Account;
import me.rn00n.oauth2.jwt.models.account.AccountRepository;
import me.rn00n.oauth2.jwt.models.account.AccountRole;
import me.rn00n.oauth2.jwt.models.account.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            AccountService accountService;
            @Autowired
            AccountRepository accountRepository;
            @Autowired
            AppProperties appProperties;
            @Override
            public void run(ApplicationArguments args) throws Exception {
                String username;
                String password;
                Optional<Account> optionalAccount;

                username = appProperties.getAdminUsername();
                password = appProperties.getAdminPassword();
                optionalAccount = accountRepository.findByEmail(username);
                if (optionalAccount.isEmpty()) {
                    Account account = Account.builder()
                            .email(username)
                            .password(password)
                            .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                            .build();

                    accountService.saveAccount(account);
                }

                username = appProperties.getUserUsername();
                password = appProperties.getUserPassword();
                optionalAccount = accountRepository.findByEmail(username);
                if (optionalAccount.isEmpty()) {
                    Account account = Account.builder()
                            .email(username)
                            .password(password)
                            .roles(Set.of(AccountRole.USER))
                            .build();

                    accountService.saveAccount(account);
                }
            }
        };
    }
}
