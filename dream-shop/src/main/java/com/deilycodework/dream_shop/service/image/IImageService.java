package com.deilycodework.dream_shop.service.image;

import com.deilycodework.dream_shop.dto.ImageDTO;
import com.deilycodework.dream_shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDTO> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);
    byte[] getImageBytesById(Long id) throws SQLException;
}
