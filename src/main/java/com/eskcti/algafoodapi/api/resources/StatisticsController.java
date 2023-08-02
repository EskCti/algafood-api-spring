package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.filter.DailySalesFilter;
import com.eskcti.algafoodapi.domain.models.DailySales;
import com.eskcti.algafoodapi.domain.services.SaleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController {
    @Autowired
    private SaleQueryService saleQueryService;

    @GetMapping("/daily-sales")
    public List<DailySales> DailySalesQuery(DailySalesFilter filter) {
        return saleQueryService.queryDailySales(filter);
    }
}
