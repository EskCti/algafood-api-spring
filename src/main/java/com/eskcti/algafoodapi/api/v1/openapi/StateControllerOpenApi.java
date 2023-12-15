package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.StateModel;
import com.eskcti.algafoodapi.api.v1.model.input.StateInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estados", description = "Gerencia os estados")
public interface StateControllerOpenApi {

    @Operation(summary = "Listar os estados")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de estados")
    )
    CollectionModel<StateModel> list();

    @Operation(summary = "Buscar um estado por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna o estado"),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID do estado inválido",
                    content = @Content(schema = @Schema(ref = "Problem"))),
    })
    StateModel find(
            @Parameter(description = "ID do estado a ser obtido", example = "1", required = true)
            Long id
    );

    @Operation(summary = "Adicionar novo estado", description = "Cadastro de um estado")
    StateModel insert(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de um novo estado", required = true)
            StateInput stateInput
    );

    @Operation(summary = "Atualizar o estado por ID")
    StateModel update(
            @Parameter(description = "ID do estado a ser atualizado", example = "1", required = true)
            Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de um estado a ser atualizado", required = true)
            StateInput stateInput
    );

    @Operation(summary = "Excluir o estado por ID")
    void remove(
            @Parameter(description = "ID do estado a ser excluido", example = "1", required = true)
            Long id
    );
}
