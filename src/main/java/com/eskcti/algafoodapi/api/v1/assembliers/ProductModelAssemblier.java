package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.model.ProductModel;
import com.eskcti.algafoodapi.api.v1.resources.RestaurantProductController;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public ProductModelAssemblier() {
        super(RestaurantProductController.class, ProductModel.class);
    }

    public ProductModel toModel(Product product) {
        ProductModel productModel = createModelWithId(product.getId(), product, product.getRestaurant().getId());
        modelMapper.map(product, productModel);

        if (algaSecurity.canConsultRestaurants()) {
            productModel.add(algaLinks.linkToProductsByRestaurant(product.getRestaurant().getId(), "products"));

            productModel.add(algaLinks.linkToPhotoOfProductByRestaurant(product.getRestaurant().getId(), product.getId(), "photo"));
        }

        return productModel;
    }

//    public List<ProductModel> toCollectionModel(List<Product> products) {
//        return products.stream()
//                .map(product -> toModel(product))
//                .collect(Collectors.toList());
//    }
}
