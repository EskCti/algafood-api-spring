package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.PaymentTypeModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Formas de Pagamentos de um restaurante", description = "Gerencia as formas de pagamentos de um restaurante")

public interface RestaurantPaymentTypeControllerOpenApi {

    @Operation(summary = "Listar as formas de pagamentos de um restaurante")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de formas de pagamentos de um restaurante")
    )
    CollectionModel<PaymentTypeModel> list(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            Long restaurantId
    );

    @Operation(summary = "Associar forma de pagamento do restaurante por ID")
    ResponseEntity<Void> associate(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            @PathVariable Long restaurantId,
            @Parameter(description = "ID da forma de pagamento", example = "1", required = true)
            @PathVariable Long paymentTypeId
    );

    @Operation(summary = "Desassociar forma de pagamento do restaurante por ID")
    ResponseEntity<Void> disassociate(
            @Parameter(description = "ID do restaurante", example = "1", required = true)
            @PathVariable Long restaurantId,
            @Parameter(description = "ID da forma de pagamento", example = "1", required = true)
            @PathVariable Long paymentTypeId
    );
}
