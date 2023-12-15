package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.GroupModel;
import com.eskcti.algafoodapi.api.v1.model.input.GroupInput;
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
@Tag(name = "Grupos", description = "Gerencia os grupos")
public interface GroupControllerOpenApi {

    @Operation(summary = "Listar os grupos")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de grupos")
    )
    CollectionModel<GroupModel> list();

    @Operation(summary = "Buscar um grupo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna o grupo"),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID do grupo inválido",
                    content = @Content(schema = @Schema(ref = "Problem"))),
    })
    GroupModel find(
            @Parameter(description = "ID do grupo a ser obtido", example = "1", required = true)
            Long id
    );

    @Operation(summary = "Adicionar novo grupo", description = "Cadastro de um grupo, necessita de um estado e um nome válido")
    GroupModel insert(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma novo grupo", required = true)
            GroupInput groupInput
    );

    @Operation(summary = "Atualizar o grupo por ID")
    GroupModel update(
            @Parameter(description = "ID do grupo a ser atualizado", example = "1", required = true)
            Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de um grupo a ser atualizado", required = true)
            GroupInput groupInput
    );

    @Operation(summary = "Excluir o grupo por ID")
    void remove(
            @Parameter(description = "ID do grupo a ser excluido", example = "1", required = true)
            Long id
    );
}
