package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.ProductModel;
import com.eskcti.algafoodapi.domain.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductModelAssemblier {

    @Autowired
    private ModelMapper modelMapper;
    public ProductModel toModel(Product product) {
        return modelMapper.map(product, ProductModel.class);
    }

    public List<ProductModel> toCollectionModel(List<Product> products) {
        return products.stream()
                .map(product -> toModel(product))
                .collect(Collectors.toList());
    }
}
