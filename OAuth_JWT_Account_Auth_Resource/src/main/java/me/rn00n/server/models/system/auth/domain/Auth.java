package me.rn00n.server.models.system.auth.domain;

import me.rn00n.server.models.base.BaseEntity;
import me.rn00n.server.models.system.resource.domain.Resource;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AUTH")
@Getter @Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor @AllArgsConstructor
public class Auth extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "auth_resource",
            joinColumns = @JoinColumn(name = "auth_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<Resource> resources;
}
