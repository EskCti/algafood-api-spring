package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.AlgaLinks;
import com.eskcti.algafoodapi.api.model.ProductModel;
import com.eskcti.algafoodapi.api.resources.RestaurantProductController;
import com.eskcti.algafoodapi.domain.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssemblier extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public ProductModelAssemblier() {
        super(RestaurantProductController.class, ProductModel.class);
    }

    public ProductModel toModel(Product product) {
        ProductModel productModel = createModelWithId(product.getId(), product, product.getRestaurant().getId());
        modelMapper.map(product, productModel);

        productModel.add(algaLinks.linkToProductByRestaurant(product.getRestaurant().getId(), "products"));

        return productModel;
    }

//    public List<ProductModel> toCollectionModel(List<Product> products) {
//        return products.stream()
//                .map(product -> toModel(product))
//                .collect(Collectors.toList());
//    }
}
