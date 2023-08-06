package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.models.ProductPhoto;
import com.eskcti.algafoodapi.domain.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogPhotoProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto photo) {
        Long productId = photo.getProductId();
        Optional<ProductPhoto> photoExists = productRepository.findPhotoById(productId);

        if (photoExists.isPresent()) {
            productRepository.delete(photoExists.get());
        };
        return productRepository.save(photo);
    }
}
