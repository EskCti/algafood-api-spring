package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.ProductPhotoModelAssemblier;
import com.eskcti.algafoodapi.api.model.ProductPhotoModel;
import com.eskcti.algafoodapi.api.model.input.ProductPhotoInput;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Product;
import com.eskcti.algafoodapi.domain.models.ProductPhoto;
import com.eskcti.algafoodapi.domain.services.CatalogPhotoProductService;
import com.eskcti.algafoodapi.domain.services.PhotoStorageService;
import com.eskcti.algafoodapi.domain.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {
    @Autowired
    private ProductService productService;
    @Autowired
    ProductPhotoModelAssemblier productPhotoModelAssemblier;
    @Autowired
    private CatalogPhotoProductService catalogPhotoProductService;

    @Autowired
    PhotoStorageService photoStorageService;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoModel findPhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        ProductPhoto productPhoto = productService.findProductPhoto(restaurantId, productId);

        return productPhotoModelAssemblier.toModel(productPhoto);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> servePhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        try {
            ProductPhoto productPhoto = productService.findProductPhoto(restaurantId, productId);

            InputStream inputStream = photoStorageService.toRecover(productPhoto.getNameFile());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
