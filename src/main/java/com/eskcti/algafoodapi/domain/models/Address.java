package com.eskcti.algafoodapi.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @NotBlank
    @Column(name = "zip_code", length = 8)
    private String zipCode;

    @NotBlank
    @Column(name = "public_place", length = 60)
    private String publicPlace;

    @NotBlank
    private String number;

    @Column(length = 60)
    private String complement;

    @NotBlank
    @Column(length = 60)
    private String neighborhood;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
