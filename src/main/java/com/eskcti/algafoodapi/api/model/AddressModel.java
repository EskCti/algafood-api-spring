package com.eskcti.algafoodapi.api.model;

import com.eskcti.algafoodapi.domain.models.City;
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
    private String zipCode;
    private String publicPlace;
    private String number;
    private String complement;
    private String neighborhood;
    private CitySummaryModel city;
}
