package me.rn00n.server.models.system.resource.domain;

import me.rn00n.server.models.base.BaseEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "RESOURCE", indexes = { @Index(name = "INDEX_Method_Url", columnList = "method,url", unique = true) })
@Inheritance(strategy=InheritanceType.JOINED)
@Getter @Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor @AllArgsConstructor
public class Resource extends BaseEntity implements GrantedAuthority {

    @Id @GeneratedValue
    private Integer id;

    private String name;

    private String url;

    private String method;

    private String description;

    @Override
    @Transient
    public String getAuthority() {
        return this.method + ":" + this.url;
    }
}
