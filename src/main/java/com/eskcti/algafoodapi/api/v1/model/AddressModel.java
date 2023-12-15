package com.eskcti.algafoodapi.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class AddressModel extends RepresentationModel<AddressModel> {
    @Schema(example = "38400-000")
    private String zipCode;

    @Schema(example = "Rua Floriano Peixoto")
    private String publicPlace;

    @Schema(example = "\"1500\"")
    private String number;

    @Schema(example = "Apto 901")
    private String complement;

    @Schema(example = "Centro")
    private String neighborhood;
    private CitySummaryModel city;
}
