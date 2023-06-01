package com.eskcti.algafoodapi.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @Column(name = "zip_code", length = 8)
    private String zipCode;

    @Column(name = "public_place", length = 60)
    private String publicPlace;

    private String number;

    @Column(length = 60)
    private String complement;

    @Column(length = 60)
    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
