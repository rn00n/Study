package me.rn00n.jwt.models.account.service;

import me.rn00n.jwt.models.account.domain.Account;
import me.rn00n.jwt.models.account.dto.AccountDto;
import me.rn00n.jwt.models.account.repository.AccountRepository;
import me.rn00n.jwt.models.authority.Authority;
import me.rn00n.jwt.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public Account signup(AccountDto userDto) {
        if (accountRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) { // 유효성 검사
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        // 권한 정보 생성
        // 빌더 패턴의 장점
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Account user = Account.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return accountRepository.save(user);
    }

    /**
     * username 을 기준으로 Account 객체와 권한객체를 가져온다
     */
    @Transactional(readOnly = true)
    public Optional<Account> getUserWithAuthorities(String username) {
        return accountRepository.findOneWithAuthoritiesByUsername(username);
    }

    /**
     * SecurityContext에 저장된 username의 정보만 가져온다
     */
    @Transactional(readOnly = true)
    public Optional<Account> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(accountRepository::findOneWithAuthoritiesByUsername);
    }
}