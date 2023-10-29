package com.eskcti.algafoodapi.api.resources.openapi;

import com.eskcti.algafoodapi.api.assembliers.ProductPhotoModelAssemblier;
import com.eskcti.algafoodapi.api.model.ProductPhotoModel;
import com.eskcti.algafoodapi.api.model.input.ProductPhotoInput;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Product;
import com.eskcti.algafoodapi.domain.models.ProductPhoto;
import com.eskcti.algafoodapi.domain.services.CatalogPhotoProductService;
import com.eskcti.algafoodapi.domain.services.PhotoStorageService;
import com.eskcti.algafoodapi.domain.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Restaurante Product Foto", description = "Gerencia as fotos dos produtos de restaurante")
public interface RestaurantProductPhotoControllerOpenApi {
    ProductPhotoModel updatePhoto(
            @PathVariable Long restaurantId,
            @PathVariable Long productId,
            @Valid ProductPhotoInput productPhotoInput
    ) throws IOException;

    @Operation(summary = "Busca a foto do produto de um restaurant", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation =  ProductPhotoModel.class)),
                    @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
                    @Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary")),
            })
    })
    ProductPhotoModel findPhoto(@PathVariable Long restaurantId, @PathVariable Long productId);

    @Operation(hidden = true)
    ResponseEntity<?> servePhoto(
            @PathVariable Long restaurantId,
            @PathVariable Long productId,
            @RequestHeader(name = "Accept") String acceptHeader
    ) throws HttpMediaTypeNotAcceptableException;

    ResponseEntity deletePhoto(@PathVariable Long restaurantId, @PathVariable Long productId);
}
