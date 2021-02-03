package me.rn00n.oauth.jwt.models.system.account.domain;

import lombok.*;
import me.rn00n.oauth.jwt.models.system.account.AccountRole;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;
}
