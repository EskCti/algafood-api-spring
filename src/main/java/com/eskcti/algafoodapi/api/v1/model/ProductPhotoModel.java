package com.eskcti.algafoodapi.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ProductPhotoModel extends RepresentationModel<ProductPhotoModel> {
    private String nameFile;
    private String description;
    private String contentType;
    private Integer fileSize;
}
