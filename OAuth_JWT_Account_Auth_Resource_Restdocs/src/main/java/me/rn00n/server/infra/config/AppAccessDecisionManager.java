package me.rn00n.server.infra.config;

import me.rn00n.server.models.system.account.domain.Account;
import me.rn00n.server.models.system.account.service.AccountService;
import me.rn00n.server.models.system.auth.domain.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Service
public class AppAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        if (new AntPathRequestMatcher("/").matches(request)) {
            return;
        }

        try {
            Iterator<? extends GrantedAuthority> authIterator = null;

            Account account = accountService.getUserDetails(authentication.getName());

            Set<Auth> auths = account.getAuths();

            authIterator = account.getAuthorities().iterator();

            while (authIterator.hasNext()) {
                GrantedAuthority grantedAuthority = authIterator.next();

                String authority = grantedAuthority.getAuthority();

                if (!authority.equals("ROLE_ANONYMOUS")) {
                    String[] methodUrl = authority.split(":");

                    if (request.getMethod().equals(methodUrl[0]) && new AntPathRequestMatcher(methodUrl[1]).matches(request)) {
                        return;
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        throw new InsufficientAuthenticationException("접속 권한이 없습니다.");
    }

    @Override
    public boolean supports(ConfigAttribute arg0) {
        return true;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }


}