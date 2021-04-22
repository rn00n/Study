package me.rn00n.models.business.imagefile.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ImageFileDto {

    @Data
    @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Create {

        /** 이름 */
        private String name;

        /** 이미지 파일 */
        private String imgFile;

    }

    @Data
    @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Response {

        private Long id;

        /** 이름 */
        private String name;

        /** 이미지 저장 경로 */
        private String img;

    }
}
