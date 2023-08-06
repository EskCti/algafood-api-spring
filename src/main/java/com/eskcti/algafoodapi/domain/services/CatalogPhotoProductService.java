package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.models.ProductPhoto;
import com.eskcti.algafoodapi.domain.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogPhotoProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto photo) {
        return productRepository.save(photo);
    }
}
