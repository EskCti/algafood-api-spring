package com.eskcti.algafoodapi.api.resources.openapi;

import com.eskcti.algafoodapi.api.model.OrderModel;
import com.eskcti.algafoodapi.api.model.OrderSummaryModel;
import com.eskcti.algafoodapi.api.model.input.OrderInput;
import com.eskcti.algafoodapi.core.openapi.PageableParameter;
import com.eskcti.algafoodapi.domain.filter.OrderFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Fluxo de Pedidos", description = "Gerencia os fluxos de pedidos")
public interface FlowOrderControllerOpenApi {

    @Operation(summary = "Confirmação de pedido", responses = {
            @ApiResponse(responseCode = "204", description = "Pedido confirmado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ResponseEntity<Void> confirm(
            @Parameter(description = "Código de um pedido", example = "04813f77-79b5-11ec-9a17-0242ac1b0002", required = true)
            String orderCode
    );

    @Operation(summary = "Registrar entrega de pedido", responses = {
            @ApiResponse(responseCode = "204", description = "Entrega de pedido registrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ResponseEntity<Void> delivery(
            @Parameter(description = "Código de um pedido", example = "04813f77-79b5-11ec-9a17-0242ac1b0002", required = true)
            String orderCode
    );

    @Operation(summary = "Cancelamento de pedido", responses = {
            @ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ResponseEntity<Void> cancel(
            @Parameter(description = "Código de um pedido", example = "04813f77-79b5-11ec-9a17-0242ac1b0002", required = true)
            String orderCode
    );
}
