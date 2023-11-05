package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.PermissionModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Permissões por grupo", description = "Gerencia as permissões por grupo")

public interface GroupPermissionControllerOpenApi {

    @Operation(summary = "Listar os estados")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de estados")
    )
    CollectionModel<PermissionModel> list(
            @Parameter(description = "ID do grupo", example = "1", required = true)
            Long groupId
    );

    @Operation(summary = "Associar permissão do grupo por ID")
    ResponseEntity<Void> associate(
            @Parameter(description = "ID do grupo", example = "1", required = true)
            @PathVariable Long groupId,
            @Parameter(description = "ID da permissão", example = "1", required = true)
            @PathVariable Long permissionId
    );

    @Operation(summary = "Desassociar permissão do grupo por ID")
    ResponseEntity<Void> disassociate(
            @Parameter(description = "ID do grupo", example = "1", required = true)
            @PathVariable Long groupId,
            @Parameter(description = "ID da permissão", example = "1", required = true)
            @PathVariable Long permissionId
    );
}
