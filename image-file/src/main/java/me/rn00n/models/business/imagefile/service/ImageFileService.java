package me.rn00n.models.business.imagefile.service;

import me.rn00n.infra.utils.FileUtil;
import me.rn00n.models.business.imagefile.domain.ImageFileEntity;
import me.rn00n.models.business.imagefile.dto.ImageFileDto;
import me.rn00n.models.business.imagefile.repository.ImageFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class ImageFileService {

    @Autowired
    private ImageFileRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileUtil fileUtil;

    public ImageFileEntity save(ImageFileDto.Create create) throws Exception {
        ImageFileEntity imageFileEntity = modelMapper.map(create, ImageFileEntity.class);

        if (!StringUtils.isEmpty(create.getImgFile()) && create.getImgFile().contains("base64")) {
            imageFileEntity.setImg(fileUtil.saveFile("image", "IMAGE", create.getImgFile()));
        }

        return repository.save(imageFileEntity);
    }
}
