package me.rn00n.models.business.imagefile.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ImageFileEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 이름 */
    private String name;

    /** 이미지 저장 경로 */
    private String img;

}
