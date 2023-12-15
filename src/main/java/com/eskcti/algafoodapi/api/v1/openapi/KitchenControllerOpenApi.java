package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.KitchenModel;
import com.eskcti.algafoodapi.api.v1.model.input.KitchenInput;
import com.eskcti.algafoodapi.core.openapi.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas", description = "Gerencia as cozinhas")
public interface KitchenControllerOpenApi {

    @Operation(summary = "Listar as cozinhas")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de cozinhas")
    )
    @PageableParameter
    PagedModel<KitchenModel> list(@Parameter(hidden = true) @PageableDefault(size = 24) Pageable pageable);

    @Operation(summary = "Buscar uma cozinha por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna a cozinha"),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID da cozinha inválido",
                    content = @Content(schema = @Schema(ref = "Problem"))),
    })
    KitchenModel find(
            @Parameter(description = "ID da cozinha a ser obtido", example = "1", required = true)
            Long id
    );

    @Operation(summary = "Adicionar nova cozinha", description = "Cadastro de uma cozinha")
    KitchenModel save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma nova cozinha", required = true)
            KitchenInput kitchenInput
    );

    @Operation(summary = "Atualizar a cozinha por ID")
    KitchenModel update(
            @Parameter(description = "ID da cozinha a ser atualizada", example = "1", required = true)
            Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma cozinha a ser atualizada", required = true)
            KitchenInput kitchenInput
    );

    @Operation(summary = "Excluir a cozinha por ID")
    void delete(
            @Parameter(description = "ID da cozinha a ser excluida", example = "1", required = true)
            Long id
    );
}
