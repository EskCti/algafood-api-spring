package com.eskcti.algafoodapi.domain.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Data
public class DailySales {
    private Date date;
    private Long totalSales;
    private BigDecimal totalBilled;

    public DailySales(Date date, Long totalSales, BigDecimal totalBilled) {
        this.date = date;
        this.totalSales = totalSales;
        this.totalBilled = totalBilled;
    }
}
