package com.eskcti.algafoodapi.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {
    @Schema(example = "João da Silva")
    @NotBlank
    private String name;
    @Schema(example = "joao@email.com")
    @Email
    @NotBlank
    private String email;
    @Schema(example = "123456Wer")
    @NotBlank
    private String password;
}
