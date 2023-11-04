package com.eskcti.algafoodapi.api.resources.openapi;

import com.eskcti.algafoodapi.api.model.ProductModel;
import com.eskcti.algafoodapi.api.model.input.ProductInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@Tag(name = "Produtos por restaurante", description = "Gerencia os produtos de restaurante")
public interface RestaurantProductControllerOpenApi {
    @Operation(summary = "Lista os produtos de um restaurante", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    CollectionModel<ProductModel> list(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            Long restaurantId,
            @Parameter(description = "Incluir inativos", example = "false", required = false)
            Boolean includeInactive
    );

    @Operation(summary = "Busca um produto de um restaurante", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ProductModel find(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            Long restaurantId,
            @Parameter(description = "ID do produto", example = "1", required = true)
            Long productId
    );

    @Operation(summary = "Deletar um produto de um restaurante", responses = {
            @ApiResponse(responseCode = "200", description = "Produto deletado"),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    void delete(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            Long restaurantId,
            @Parameter(description = "ID do produto", example = "1", required = true)
            Long productId
    );

    @Operation(summary = "Cadastra um produto de um restaurante", responses = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ProductModel insert(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            Long restaurantId,
            @RequestBody(description = "Representação de um novo produto", required = true)
            ProductInput productInput
    );

    @Operation(summary = "Atualiza um produto de um restaurante", responses = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado"),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ProductModel update(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            Long restaurantId,
            @Parameter(description = "ID do produto", example = "1", required = true)
            Long productId,
            @RequestBody(description = "Representação de um novo produto", required = true)
            ProductInput productInput
    );
}
