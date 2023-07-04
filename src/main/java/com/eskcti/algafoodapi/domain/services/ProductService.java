package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.ProductNotFoundException;
import com.eskcti.algafoodapi.domain.exceptions.RestaurantAndProductNotFoundException;
import com.eskcti.algafoodapi.domain.models.Product;
import com.eskcti.algafoodapi.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    public static final String PRODUCT_REMOVED_IN_USE = "Product with id %d not removed in use ";
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

    @Transactional
    public void remove(Long id) {
        try {
            find(id);
            productRepository.deleteById(id);
            productRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(PRODUCT_REMOVED_IN_USE, id));
        }
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
