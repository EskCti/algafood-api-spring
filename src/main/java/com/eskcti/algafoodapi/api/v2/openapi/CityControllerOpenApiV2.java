package com.eskcti.algafoodapi.api.v2.openapi;

import com.eskcti.algafoodapi.api.v2.model.CityModelV2;
import com.eskcti.algafoodapi.api.v2.model.input.CityInputV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@Tag(name = "Cidades", description = "Gerencia as cidades")

public interface CityControllerOpenApiV2 {

    @Operation(summary = "Listar as cidades")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de cidades")
    )
    CollectionModel<CityModelV2> list();

    @Operation(summary = "Buscar uma cidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna a cidade"),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID da cidade inválida",
                    content = @Content(schema = @Schema(ref = "Problem"))),
    })
    CityModelV2 find(
            @Parameter(description = "ID da cidade a ser obtido", example = "1", required = true)
            Long id
    );

    @Operation(summary = "Adicionar nova cidade", description = "Cadastro de uma cidade, necessita de um estado e um nome válido")
    CityModelV2 insert(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma nova cidade", required = true)
            CityInputV2 cityInput
    );

    @Operation(summary = "Atualizar a cidade por ID")
    CityModelV2 update(
            @Parameter(description = "ID da cidade a ser atualizada", example = "1", required = true)
            Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma cidade a ser atualizada", required = true)
            CityInputV2 cityInput
    );

    @Operation(summary = "Excluir a cidade por ID")
    void delete(
            @Parameter(description = "ID da cidade a ser excluida", example = "1", required = true)
            Long id
    );
}
