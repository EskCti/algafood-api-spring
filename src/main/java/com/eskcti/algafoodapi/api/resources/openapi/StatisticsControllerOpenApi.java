package com.eskcti.algafoodapi.api.resources.openapi;

import com.eskcti.algafoodapi.api.model.OrderModel;
import com.eskcti.algafoodapi.api.model.OrderSummaryModel;
import com.eskcti.algafoodapi.api.model.input.OrderInput;
import com.eskcti.algafoodapi.api.resources.StatisticsController;
import com.eskcti.algafoodapi.core.openapi.PageableParameter;
import com.eskcti.algafoodapi.domain.filter.DailySalesFilter;
import com.eskcti.algafoodapi.domain.filter.OrderFilter;
import com.eskcti.algafoodapi.domain.models.DailySales;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

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
