package com.eskcti.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoModel {
    private String nameFile;
    private String description;
    private String contentType;
    private Integer fileSize;
}
