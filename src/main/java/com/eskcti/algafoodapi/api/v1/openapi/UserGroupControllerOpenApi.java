package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.GroupModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos de um usuário", description = "Gerencia os grupos de um usuário")
public interface UserGroupControllerOpenApi {

    @Operation(summary = "Listar os grupos de um usuário")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de grupos de um usuário")
    )
    CollectionModel<GroupModel> list(
            @Parameter(description = "ID do usuário", example = "1", required = true)
            Long userId
    );

    @Operation(summary = "Associar grupo com usuário por ID")
    ResponseEntity<Void> associate(
            @Parameter(description = "ID do usuário", example = "1", required = true)
            @PathVariable Long userId,
            @Parameter(description = "ID do grupo", example = "1", required = true)
            @PathVariable Long groupId
    );

    @Operation(summary = "Desassociar grupo do usuário por ID")
    ResponseEntity<Void> disassociate(
            @Parameter(description = "ID do usuário", example = "1", required = true)
            @PathVariable Long userId,
            @Parameter(description = "ID da grupo", example = "1", required = true)
            @PathVariable Long groupId
    );
}
