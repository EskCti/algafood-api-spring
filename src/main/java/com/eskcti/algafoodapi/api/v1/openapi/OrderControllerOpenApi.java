package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.OrderModel;
import com.eskcti.algafoodapi.api.v1.model.OrderSummaryModel;
import com.eskcti.algafoodapi.api.v1.model.input.OrderInput;
import com.eskcti.algafoodapi.core.openapi.PageableParameter;
import com.eskcti.algafoodapi.domain.filter.OrderFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos", description = "Gerencia os Pedidos")
public interface OrderControllerOpenApi {

    @Operation(
            summary = "Pesquisa os pedidos",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "customerId",
                            description = "ID do cliente para filtro da pesquisa",
                            example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "restaurantId",
                            description = "ID do restaurante para filtro da pesquisa",
                            example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "dateFrom",
                            description = "Data/hora de criação inicial para filtro da pesquisa",
                            example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(in = ParameterIn.QUERY, name = "dateTo",
                            description = "Data/hora de criação final para filtro da pesquisa",
                            example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
            }
    )
    @PageableParameter
    PagedModel<OrderSummaryModel> list(
            @Parameter(hidden = true) OrderFilter orderFilter,
            @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Buscar um pedido por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna o pedido"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Código do pedido inválido",
                    content = @Content(schema = @Schema(ref = "Problem"))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido não encontrado",
                    content = @Content(schema = @Schema(ref = "Problem"))),
    })
    OrderModel find(@PathVariable String orderCode);

    @Operation(summary = "Registra um pedido", responses = {
            @ApiResponse(responseCode = "201", description = "Pedido registrado"),
    })
    OrderModel add(
            @Valid @RequestBody OrderInput orderInput);
}
