package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.resources.StatisticsController;
import com.eskcti.algafoodapi.domain.filter.DailySalesFilter;
import com.eskcti.algafoodapi.domain.models.DailySales;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatistica de vendas", description = "Estatisticas de vendas")
public interface StatisticsControllerOpenApi {

    @Operation(
            summary = "Pesquisa estatisticas das vendas",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "restaurantId",
                            description = "ID do restaurante para filtro da pesquisa",
                            example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "dateFrom",
                            description = "Data/hora inicial para filtro da pesquisa",
                            example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(in = ParameterIn.QUERY, name = "dateTo",
                            description = "Data/hora final para filtro da pesquisa",
                            example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
            }
    )
    List<DailySales> DailySalesQuery(
            @Parameter(hidden = true) DailySalesFilter filter,
            String timeOffset
    );

    @Operation(summary = "Exibe vendas do dia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna o pedido"),
    })
    StatisticsController.StatisticsModel statistics();
}
