package com.eskcti.algafoodapi.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatePassword {
    @NotBlank
    private String currentPassword;
    @NotBlank
    private String newPassword;
}
