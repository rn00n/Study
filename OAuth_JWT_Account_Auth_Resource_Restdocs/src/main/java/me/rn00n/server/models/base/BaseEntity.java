package me.rn00n.server.models.base;

import lombok.*;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter
public class BaseEntity {

    private String useYn = "Y";

}
