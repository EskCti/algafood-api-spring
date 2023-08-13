package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.models.ProductPhoto;
import com.eskcti.algafoodapi.domain.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogPhotoProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Transactional
    public ProductPhoto save(ProductPhoto photo, InputStream dataFile) {
        Long productId = photo.getProductId();
        Long restaurantId = photo.getProduct().getRestaurant().getId();
        Optional<ProductPhoto> photoExists = productRepository.findPhotoById(restaurantId, productId);
        String newNameFile = photoStorageService.generateFileName(photo.getNameFile());

        if (photoExists.isPresent()) {
            productRepository.delete(photoExists.get());
            photoStorageService.remove(photoExists.get().getNameFile());
        };

        photo.setNameFile(newNameFile);
        photo = productRepository.save(photo);
        productRepository.flush();

        PhotoStorageService.NewPhoto newPhoto = PhotoStorageService
                .NewPhoto.builder()
                .nameFile(photo.getNameFile())
                .contentType(photo.getContentType())
                .inputStream(dataFile)
                .build();

        photoStorageService.storage(newPhoto);

        return photo;
    }

    @Transactional
    public void delete(ProductPhoto photo) {
        Long productId = photo.getProductId();
        Long restaurantId = photo.getProduct().getRestaurant().getId();
        Optional<ProductPhoto> photoExists = productRepository.findPhotoById(restaurantId, productId);

        if (photoExists.isPresent()) {
            productRepository.delete(photoExists.get());
            photoStorageService.remove(photoExists.get().getNameFile());
        };
    }
}
