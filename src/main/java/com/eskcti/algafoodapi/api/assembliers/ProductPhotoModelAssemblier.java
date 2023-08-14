package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.ProductPhotoModel;
import com.eskcti.algafoodapi.api.model.StateModel;
import com.eskcti.algafoodapi.domain.models.ProductPhoto;
import com.eskcti.algafoodapi.domain.models.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductPhotoModelAssemblier {
    @Autowired
    private ModelMapper modelMapper;
    public ProductPhotoModel toModel(ProductPhoto photo) {
        return modelMapper.map(photo, ProductPhotoModel.class);
    }

    public List<ProductPhotoModel> toCollectionModel(List<ProductPhoto> photos) {
        return photos.stream()
                .map(photo -> toModel(photo))
                .collect(Collectors.toList());
    }
}
