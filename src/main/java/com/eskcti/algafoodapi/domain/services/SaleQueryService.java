package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.filter.DailySalesFilter;
import com.eskcti.algafoodapi.domain.models.DailySales;

import java.util.List;

public interface SaleQueryService {
    List<DailySales> queryDailySales(DailySalesFilter filter);
}
