package me.rn00n.jwt.models.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import me.rn00n.jwt.models.authority.Authority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Account {

    @JsonIgnore
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
            )
    private Set<Authority> authorities;

}
