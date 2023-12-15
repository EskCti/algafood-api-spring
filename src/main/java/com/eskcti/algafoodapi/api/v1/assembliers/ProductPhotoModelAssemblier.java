package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.model.ProductPhotoModel;
import com.eskcti.algafoodapi.api.v1.resources.RestaurantProductPhotoController;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
import com.eskcti.algafoodapi.domain.models.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductPhotoModelAssemblier extends RepresentationModelAssemblerSupport<ProductPhoto, ProductPhotoModel> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public ProductPhotoModelAssemblier() {
        super(RestaurantProductPhotoController.class, ProductPhotoModel.class);
    }
    public ProductPhotoModel toModel(ProductPhoto photo) {
        ProductPhotoModel productPhotoModel = modelMapper.map(photo, ProductPhotoModel.class);
        if (algaSecurity.canConsultRestaurants()) {
            productPhotoModel.add(algaLinks.linkToPhotoOfProductByRestaurant(photo.getRestaurantId(), photo.getProductId(), "photo"));
            productPhotoModel.add(algaLinks.linkToProductByRestaurant(photo.getRestaurantId(), photo.getProductId(), "product"));
        }

        return productPhotoModel;
    }
//    public List<ProductPhotoModel> toCollectionModel(List<ProductPhoto> photos) {
//        return photos.stream()
//                .map(photo -> toModel(photo))
//                .collect(Collectors.toList());
//    }
}
