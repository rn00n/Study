package me.rn00n.jwt.models.account.controller;

import me.rn00n.jwt.models.account.domain.Account;
import me.rn00n.jwt.models.account.dto.AccountDto;
import me.rn00n.jwt.models.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Account> signup(
            @Valid @RequestBody AccountDto accountDto
    ) {
        return ResponseEntity.ok(accountService.signup(accountDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Account> getMyUserInfo() {
        return ResponseEntity.ok(accountService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Account> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(accountService.getUserWithAuthorities(username).get());
    }
}
