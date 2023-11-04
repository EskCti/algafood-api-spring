package com.eskcti.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CitySummaryModel extends RepresentationModel<CitySummaryModel> {
    private Long id;

    private String name;

    private String state;

}
