package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.ProductPhoto;

public interface ProductRepositoryQueries {
    ProductPhoto save(ProductPhoto photo);
    void delete(ProductPhoto photo);
}
