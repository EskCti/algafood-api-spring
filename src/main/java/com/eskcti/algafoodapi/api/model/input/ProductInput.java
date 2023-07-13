package com.eskcti.algafoodapi.api.model.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductInput {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull
    @NotBlank
    private Boolean active;
}
