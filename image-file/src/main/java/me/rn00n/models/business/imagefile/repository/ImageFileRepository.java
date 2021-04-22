package me.rn00n.models.business.imagefile.repository;

import me.rn00n.models.business.imagefile.domain.ImageFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<ImageFileEntity, Long> {
}
