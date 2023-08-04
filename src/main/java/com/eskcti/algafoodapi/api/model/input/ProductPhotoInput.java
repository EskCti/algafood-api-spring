package com.eskcti.algafoodapi.api.model.input;

import com.eskcti.algafoodapi.core.validation.FileSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductPhotoInput {
    @NotNull
    @FileSize(max = "500KB")
    private MultipartFile file;
    @NotBlank
    private String description;
}
