package com.eskcti.algafoodapi.api.model;

import com.eskcti.algafoodapi.domain.models.City;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
