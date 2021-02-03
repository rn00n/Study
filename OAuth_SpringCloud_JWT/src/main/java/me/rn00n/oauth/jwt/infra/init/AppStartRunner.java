package me.rn00n.oauth.jwt.infra.init;

import me.rn00n.oauth.jwt.models.system.account.AccountRole;
import me.rn00n.oauth.jwt.models.system.account.domain.Account;
import me.rn00n.oauth.jwt.models.system.account.repository.AccountRepository;
import me.rn00n.oauth.jwt.models.system.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AppStartRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Account> accountOptional;

        accountOptional = accountRepository.findByUsername("admin");
        if (accountOptional.isEmpty()) {
            Account account = Account.builder()
                    .username("admin")
                    .password(new BCryptPasswordEncoder().encode("1234"))
                    .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                    .build();

            accountService.saveAccount(account);
        }

        accountOptional = accountRepository.findByUsername("user");
        if (accountOptional.isEmpty()) {
            Account account = Account.builder()
                    .username("user")
                    .password(new BCryptPasswordEncoder().encode("1234"))
                    .roles(Set.of(AccountRole.USER))
                    .build();

            accountService.saveAccount(account);
        }
    }
}
