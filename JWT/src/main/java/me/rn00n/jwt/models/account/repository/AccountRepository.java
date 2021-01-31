package me.rn00n.jwt.models.account.repository;

import me.rn00n.jwt.models.account.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * @EntityGraph 은 쿼리가 수행이 될 때 Lazy 조회가 아니고 Eager 조회로 authorities 정보를 같이 가져온다
     * * Set<> 컬렉션만 가능
     */
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByUsername(String username);
}
