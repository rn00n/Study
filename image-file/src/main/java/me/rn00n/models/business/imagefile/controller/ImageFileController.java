package me.rn00n.models.business.imagefile.controller;

import me.rn00n.infra.error.exception.BadRequestException;
import me.rn00n.models.business.imagefile.domain.ImageFileEntity;
import me.rn00n.models.business.imagefile.dto.ImageFileDto;
import me.rn00n.models.business.imagefile.service.ImageFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/image-files", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
public class ImageFileController {

    @Autowired
    private ImageFileService imageFileService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ImageFileDto.Create create, Errors errors) throws Exception {
        validRequest(errors);

        ImageFileEntity imageFileEntity = imageFileService.save(create);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(imageFileEntity, ImageFileDto.Response.class));
    }

    private void validRequest(Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException("Bad Request Exception", errors);
        }
    }
}
