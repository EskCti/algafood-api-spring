package com.eskcti.algafoodapi.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdate {
    @Schema(example = "João da Silva")
    @NotBlank
    private String name;
    @Schema(example = "joao@email.com")
    @Email
    @NotBlank
    private String email;
}
