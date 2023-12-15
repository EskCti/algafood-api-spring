package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.assembliers.ProductPhotoModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.ProductPhotoModel;
import com.eskcti.algafoodapi.api.v1.model.input.ProductPhotoInput;
import com.eskcti.algafoodapi.api.v1.openapi.RestaurantProductPhotoControllerOpenApi;
import com.eskcti.algafoodapi.core.security.CheckSecutiry;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Product;
import com.eskcti.algafoodapi.domain.models.ProductPhoto;
import com.eskcti.algafoodapi.domain.services.CatalogPhotoProductService;
import com.eskcti.algafoodapi.domain.services.PhotoStorageService;
import com.eskcti.algafoodapi.domain.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController implements RestaurantProductPhotoControllerOpenApi {
    @Autowired
    private ProductService productService;
    @Autowired
    ProductPhotoModelAssemblier productPhotoModelAssemblier;
    @Autowired
    private CatalogPhotoProductService catalogPhotoProductService;

    @Autowired
    PhotoStorageService photoStorageService;

    @CheckSecutiry.Restaurants.CanManager
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

    @CheckSecutiry.Restaurants.CanConsult
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoModel findPhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        ProductPhoto productPhoto = productService.findProductPhoto(restaurantId, productId);

        return productPhotoModelAssemblier.toModel(productPhoto);
    }

    @CheckSecutiry.Restaurants.CanConsult
    @GetMapping
    public ResponseEntity<?> servePhoto(
            @PathVariable Long restaurantId,
            @PathVariable Long productId,
            @RequestHeader(name = "Accept") String acceptHeader
    ) throws HttpMediaTypeNotAcceptableException {
        try {
            ProductPhoto productPhoto = productService.findProductPhoto(restaurantId, productId);

            MediaType mediaType = MediaType.parseMediaType(productPhoto.getContentType());
            List<MediaType> mediaTypeList = MediaType.parseMediaTypes(acceptHeader);

            checkMediaTypeCompatibility(mediaType, mediaTypeList);

            PhotoStorageService.RecoveredPhoto recoveredPhoto = photoStorageService.toRecover(productPhoto.getNameFile());

            if (recoveredPhoto.haveUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, recoveredPhoto.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaType)
                        .body(new InputStreamResource(recoveredPhoto.getInputStream()));
            }

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CheckSecutiry.Restaurants.CanManager
    @DeleteMapping
    public ResponseEntity deletePhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        ProductPhoto productPhoto = productService.findProductPhoto(restaurantId, productId);

        catalogPhotoProductService.delete(productPhoto);

        return ResponseEntity.noContent().build();
    }

    private void checkMediaTypeCompatibility(MediaType mediaType, List<MediaType> mediaTypeList)
            throws HttpMediaTypeNotAcceptableException {
        boolean compatibility = mediaTypeList.stream()
                .anyMatch(mediaTypeAccept -> mediaTypeAccept.isCompatibleWith(mediaType));
        if (!compatibility) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypeList);
        }
    }
}
