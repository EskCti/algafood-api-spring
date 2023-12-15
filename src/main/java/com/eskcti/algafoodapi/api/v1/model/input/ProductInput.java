package com.eskcti.algafoodapi.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductInput {
    @Schema(example = "Espetinho de Cupim")
    @NotBlank
    private String name;
    @Schema(example = "Acompanha farinha, mandioca e vinagrete")
    @NotBlank
    private String description;
    @Schema(example = "12.50")
    @NotNull
    @Min(0)
    private BigDecimal price;
    @Schema(example = "true")
    @NotNull
    @NotBlank
    private Boolean active;
}
