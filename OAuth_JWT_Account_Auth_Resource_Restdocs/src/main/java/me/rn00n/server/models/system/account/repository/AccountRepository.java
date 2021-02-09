package me.rn00n.server.models.system.account.repository;

import me.rn00n.server.models.base.BaseRepository;
import me.rn00n.server.models.system.account.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);

    @EntityGraph(attributePaths = {"auths", "auths.resources"})
    Optional<Account> findAccountWithAuthAndResourceByUsername(String username);
}
