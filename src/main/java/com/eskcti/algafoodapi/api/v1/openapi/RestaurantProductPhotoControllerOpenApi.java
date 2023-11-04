package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.ProductPhotoModel;
import com.eskcti.algafoodapi.api.v1.model.input.ProductPhotoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Restaurante Product Foto", description = "Gerencia as fotos dos produtos de restaurante")
public interface RestaurantProductPhotoControllerOpenApi {
    @Operation(summary = "Atualiza a foto do produto de um restaurant")
    ProductPhotoModel updatePhoto(
            @Parameter(description = "Id do restaurant", example = "1", required = true)
            @PathVariable Long restaurantId,
            @Parameter(description = "Id do produto", example = "1", required = true)
            @PathVariable Long productId,
            @RequestBody(required = true)
            @Valid ProductPhotoInput productPhotoInput
    ) throws IOException;

    @Operation(summary = "Busca a foto do produto de um restaurant", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation =  ProductPhotoModel.class)),
                    @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
                    @Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary")),
            })
    })
    ProductPhotoModel findPhoto(
            @Parameter(description = "Id do restaurant", example = "1", required = true)
            Long restaurantId,
            @Parameter(description = "Id do produto", example = "1", required = true)
            Long productId
    );

    @Operation(hidden = true)
    ResponseEntity<?> servePhoto(
            @Parameter(description = "Id do restaurant", example = "1", required = true)
            Long restaurantId,
            @Parameter(description = "Id do produto", example = "1", required = true)
            Long productId,
            @RequestHeader(name = "Accept") String acceptHeader
    ) throws HttpMediaTypeNotAcceptableException;

    ResponseEntity deletePhoto(
            @Parameter(description = "Id do restaurant", example = "1", required = true)
            Long restaurantId,
            @Parameter(description = "Id do produto", example = "1", required = true)
            Long productId
    );
}
