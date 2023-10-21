package com.eskcti.algafoodapi.api.resources.openapi;

import com.eskcti.algafoodapi.api.model.CityModel;
import com.eskcti.algafoodapi.api.model.input.CityInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@Tag(name = "Cidades", description = "Gerencia as cidades")

public interface CityControllerOpenApi {

    @Operation(summary = "Listar as cidades")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de cidades")
    )
    CollectionModel<CityModel> list();

    @Operation(summary = "Buscar uma cidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna a cidade"),
    })
    CityModel find(
            @Parameter(description = "ID da cidade a ser obtido", example = "1")
            Long id
    );

    @Operation(summary = "Adicionar nova cidade")
    CityModel insert(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma nova cidade")
            CityInput cityInput
    );

    @Operation(summary = "Atualizar a cidade por ID")
    CityModel update(
            @Parameter(description = "ID da cidade a ser atualizada")
            Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma cidade a ser atualizada")
            CityInput cityInput
    );

    @Operation(summary = "Excluir a cidade por ID")
    void delete(
            @Parameter(description = "ID da cidade a ser excluida")
            Long id
    );
}
