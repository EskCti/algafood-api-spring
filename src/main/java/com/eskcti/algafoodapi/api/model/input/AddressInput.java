package com.eskcti.algafoodapi.api.model.input;

import com.eskcti.algafoodapi.api.model.CitySummaryModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class AddressInput {
    @NotBlank
    private String zipCode;
    @NotBlank
    private String publicPlace;
    @NotBlank
    private String number;
    private String complement;
    @NotBlank
    private String neighborhood;
    @Valid
    @NotNull
    private CityIdInput city;
}
