package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.input.ProductInput;
import com.eskcti.algafoodapi.domain.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Product toDomainObject(ProductInput productInput) {
        return modelMapper.map(productInput, Product.class);
    }

    public void copyToDomainObject(ProductInput productInput, Product product) {
        modelMapper.map(productInput, product);
    }
}
