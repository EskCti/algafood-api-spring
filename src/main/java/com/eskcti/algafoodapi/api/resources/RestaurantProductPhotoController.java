package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.model.input.ProductPhotoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(
            @PathVariable Long restaurantId,
            @PathVariable Long productId,
            ProductPhotoInput productPhotoInput
    ) {
        var nameFile = UUID.randomUUID().toString()
                + "_" + productPhotoInput.getFile().getOriginalFilename();

        var filePhoto = Path.of("/home/eskokado/Projetos/Algaworks/uploads", nameFile);

        System.out.println(filePhoto);
        System.out.println(productPhotoInput.getFile().getContentType());
        System.out.println(productPhotoInput.getDescription());

        try {
            productPhotoInput.getFile().transferTo(filePhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
