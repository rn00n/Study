package me.rn00n.server.infra.config;

import me.rn00n.server.models.system.account.domain.Account;
import me.rn00n.server.models.system.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;

        if (!ObjectUtils.isEmpty(accountService)) {
            userDetails = accountService.getUserDetails(username);
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException("아이디, 비밀번호를 정확히 입력 하세요.");
        }

        Account account = accountService.findOneByUsername(userDetails.getUsername());
        account.setLastLoginDateTime(LocalDateTime.now());

        return userDetails;
    }
}
