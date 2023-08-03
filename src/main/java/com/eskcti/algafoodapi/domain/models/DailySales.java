package com.eskcti.algafoodapi.domain.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Data
public class DailySales {
    private LocalDate date;
    private Long totalSales;
    private BigDecimal totalBilled;

    public DailySales(LocalDate date, Long totalSales, BigDecimal totalBilled) {
        this.date = date;
        this.totalSales = totalSales;
        this.totalBilled = totalBilled;
    }
}
