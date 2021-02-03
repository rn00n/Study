package me.rn00n.oauth.jwt.models.system.account.repository;

import me.rn00n.oauth.jwt.models.system.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
