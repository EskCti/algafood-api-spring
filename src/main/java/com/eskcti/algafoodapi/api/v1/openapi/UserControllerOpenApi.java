package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.UserModel;
import com.eskcti.algafoodapi.api.v1.model.input.UserInput;
import com.eskcti.algafoodapi.api.v1.model.input.UserUpdate;
import com.eskcti.algafoodapi.api.v1.model.input.UserUpdatePassword;
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
@Tag(name = "Usuários", description = "Gerencia os usuários")
public interface UserControllerOpenApi {

    @Operation(summary = "Listar os usuários")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de usuários")
    )
    CollectionModel<UserModel> list();

    @Operation(summary = "Buscar um usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna o usuário"),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID do usuário inválido",
                    content = @Content(schema = @Schema(ref = "Problem"))),
    })
    UserModel find(
            @Parameter(description = "ID do usuário a ser obtido", example = "1", required = true)
            Long id
    );

    @Operation(summary = "Adicionar novo usuário", description = "Cadastro de um novo usuário")
    UserModel insert(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de um novo usuário", required = true)
            UserInput userInput
    );

    @Operation(summary = "Atualizar o usuário por ID")
    UserModel update(
            @Parameter(description = "ID do usuário a ser atualizada", example = "1", required = true)
            Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de um usuário a ser atualizada", required = true)
            UserUpdate userUpdate
    );

    @Operation(summary = "Excluir o usuário por ID")
    void delete(
            @Parameter(description = "ID do usuário a ser excluida", example = "1", required = true)
            Long id
    );

    @Operation(summary = "Alterar senha do usuário por ID")
    void updatePassword(
            @Parameter(description = "ID do usuário", example = "1", required = true)
            Long id,
            @Parameter(description = "Senha do usuário a ser alterado", example = "sdff2344545#", required = true)
            UserUpdatePassword userUpdatePassword
    );
}
