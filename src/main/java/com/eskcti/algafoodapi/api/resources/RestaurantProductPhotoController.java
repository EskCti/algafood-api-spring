package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.ProductPhotoModelAssemblier;
import com.eskcti.algafoodapi.api.model.ProductPhotoModel;
import com.eskcti.algafoodapi.api.model.input.ProductPhotoInput;
import com.eskcti.algafoodapi.domain.models.Product;
import com.eskcti.algafoodapi.domain.models.ProductPhoto;
import com.eskcti.algafoodapi.domain.services.CatalogPhotoProductService;
import com.eskcti.algafoodapi.domain.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {
    @Autowired
    private ProductService productService;
    @Autowired
    ProductPhotoModelAssemblier productPhotoModelAssemblier;
    @Autowired
    private CatalogPhotoProductService catalogPhotoProductService;
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoModel updatePhoto(
            @PathVariable Long restaurantId,
            @PathVariable Long productId,
            @Valid ProductPhotoInput productPhotoInput
    ) throws IOException {
        Product product = productService.findByRestaurantIdAndId(restaurantId, productId);

        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProductId(product.getId());
        photo.setProduct(product);
        photo.setDescription(productPhotoInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setFileSize((int) file.getSize());
        photo.setNameFile(file.getOriginalFilename());

        ProductPhoto photoSave = catalogPhotoProductService.save(photo, file.getInputStream());

        return productPhotoModelAssemblier.toModel(photoSave);
    }

    @GetMapping
    public ProductPhotoModel findPhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        ProductPhoto productPhoto = productService.findProductPhoto(restaurantId, productId);

        return productPhotoModelAssemblier.toModel(productPhoto);
    }
}
