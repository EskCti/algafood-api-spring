package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.ProductNotFoundException;
import com.eskcti.algafoodapi.domain.exceptions.RestaurantAndProductNotFoundException;
import com.eskcti.algafoodapi.domain.models.Product;
import com.eskcti.algafoodapi.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product find(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product findByRestaurantIdAndId(Long restaurantId, Long productId) {
        return productRepository.findByRestaurantIdAndId(restaurantId, productId)
                .orElseThrow(() -> new RestaurantAndProductNotFoundException(restaurantId, productId));
    }
}
