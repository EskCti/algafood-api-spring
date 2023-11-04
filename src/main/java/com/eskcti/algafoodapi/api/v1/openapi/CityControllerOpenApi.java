package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.CityModel;
import com.eskcti.algafoodapi.api.v1.model.input.CityInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
            @ApiResponse(
                    responseCode = "400",
                    description = "ID da cidade inválida",
                    content = @Content(schema = @Schema(ref = "Problem"))),
    })
    CityModel find(
            @Parameter(description = "ID da cidade a ser obtido", example = "1", required = true)
            Long id
    );

    @Operation(summary = "Adicionar nova cidade", description = "Cadastro de uma cidade, necessita de um estado e um nome válido")
    CityModel insert(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma nova cidade", required = true)
            CityInput cityInput
    );

    @Operation(summary = "Atualizar a cidade por ID")
    CityModel update(
            @Parameter(description = "ID da cidade a ser atualizada", example = "1", required = true)
            Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma cidade a ser atualizada", required = true)
            CityInput cityInput
    );

    @Operation(summary = "Excluir a cidade por ID")
    void delete(
            @Parameter(description = "ID da cidade a ser excluida", example = "1", required = true)
            Long id
    );
}
