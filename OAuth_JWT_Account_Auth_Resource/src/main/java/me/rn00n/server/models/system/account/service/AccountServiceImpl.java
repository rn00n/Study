package me.rn00n.server.models.system.account.service;

import me.rn00n.server.models.base.BaseService;
import me.rn00n.server.models.system.account.domain.Account;
import me.rn00n.server.models.system.account.dto.AccountSignUp;
import me.rn00n.server.models.system.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl extends BaseService<Account, Integer> implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account saveAccount(AccountSignUp accountSignUp) {
        Account account = modelMapper.map(accountSignUp, Account.class);
        account.setPassword(passwordEncoder.encode(accountSignUp.getPassword()));
        return repository.save(account);
    }

    public Account getUserDetails(String username) {
        Optional<Account> accountOptional = repository.findAccountWithAuthAndResourceByUsername(username);
        return accountOptional.orElse(null);
    }

    public Account findOneByUsername(String username) {
        Optional<Account> accountOptional = repository.findByUsername(username);
        return accountOptional.orElse(null);
    }

}