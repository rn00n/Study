package me.rn00n.server.models.system.account.domain;

import me.rn00n.server.models.base.BaseDateTimeEntity;
import me.rn00n.server.models.system.auth.domain.Auth;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
@Getter @Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor @AllArgsConstructor
public class Account extends BaseDateTimeEntity implements UserDetails {

    @Id @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String phoneNo;

    private String email;

    private String countryNo;

    private String recommender;

    private String profileImg;

    private String profileImgFile;

    private LocalDateTime lastLoginDateTime;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_auth",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id")
    )
    private Set<Auth> auths = new HashSet<>();

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (getAuths() != null) {
            getAuths().forEach(auth -> {
                grantedAuthorities.addAll(auth.getResources());
            });
        }
        return grantedAuthorities;
    }

    @Override
    @Transient
    public String getPassword() {
        return this.password;
    }

    @Override
    @Transient
    public String getUsername() {
        return this.username;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}
