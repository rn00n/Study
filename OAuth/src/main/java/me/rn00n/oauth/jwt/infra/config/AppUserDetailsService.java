package me.rn00n.oauth.jwt.infra.config;

import me.rn00n.oauth.jwt.models.system.account.AccountAdapter;
import me.rn00n.oauth.jwt.models.system.account.domain.Account;
import me.rn00n.oauth.jwt.models.system.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new AccountAdapter(account);
    }
}
