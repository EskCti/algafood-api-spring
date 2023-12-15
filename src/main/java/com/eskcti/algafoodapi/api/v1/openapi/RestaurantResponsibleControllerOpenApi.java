package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.UserModel;
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
@Tag(name = "Restaurantes com responsáveis", description = "Gerencia os restaurantes com responsáveis")
public interface RestaurantResponsibleControllerOpenApi {

    @Operation(summary = "Listar os responsáveis de um restaurante")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de responsáveis de um restaurante")
    )
    CollectionModel<UserModel> list(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            Long restaurantId
    );

    @Operation(summary = "Associar restaurante com responsável por ID")
    ResponseEntity<Void> associate(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            @PathVariable Long restauranteId,
            @Parameter(description = "ID do responsável", example = "1", required = true)
            @PathVariable Long userId
    );

    @Operation(summary = "Desassociar restaurante com responsável por ID")
    ResponseEntity<Void> disassociate(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            @PathVariable Long restaurantId,
            @Parameter(description = "ID do responsável", example = "1", required = true)
            @PathVariable Long userId
    );
}
