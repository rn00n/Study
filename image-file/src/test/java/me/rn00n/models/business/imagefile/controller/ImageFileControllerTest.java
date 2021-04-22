package me.rn00n.models.business.imagefile.controller;

import me.rn00n.infra.commons.BaseControllerTest;
import me.rn00n.models.business.imagefile.dto.ImageFileDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ImageFileControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("이미지파일 등록 - 성공")
    void addImageFile_Success() throws Exception {
        String imgFile = "";
        ImageFileDto.Create create = ImageFileDto.Create.builder()
                .name("이미지")
                .imgFile(imgFile)
                .build();
        ResultActions resultActions = mockMvc.perform(post("/api/image-files")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(create))
        ).andDo(print());

        resultActions
                .andExpect(status().isCreated())

                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("이미지"))
                .andExpect(jsonPath("img").exists())
        ;
    }
}