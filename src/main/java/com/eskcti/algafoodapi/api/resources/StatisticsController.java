package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.AlgaLinks;
import com.eskcti.algafoodapi.api.resources.openapi.StatisticsControllerOpenApi;
import com.eskcti.algafoodapi.domain.filter.DailySalesFilter;
import com.eskcti.algafoodapi.domain.models.DailySales;
import com.eskcti.algafoodapi.domain.services.SaleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController implements StatisticsControllerOpenApi {
    @Autowired
    private SaleQueryService saleQueryService;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticsModel statistics() {
        var statisticsModel = new StatisticsModel();

        statisticsModel.add(algaLinks.linkToStatisticsDailySales("daily-sales"));

        return statisticsModel;
    }

    @GetMapping("/daily-sales")
    public List<DailySales> DailySalesQuery(
            DailySalesFilter filter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffset
    ) {
        return saleQueryService.queryDailySales(filter, timeOffset);
    }

    public static class StatisticsModel extends RepresentationModel<StatisticsModel> {
    }

}
