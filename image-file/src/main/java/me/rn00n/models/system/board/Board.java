package me.rn00n.models.system.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id", callSuper = false)
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
