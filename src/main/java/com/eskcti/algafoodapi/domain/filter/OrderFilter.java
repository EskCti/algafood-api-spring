package com.eskcti.algafoodapi.domain.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Getter
@Setter
public class OrderFilter {
    private Long customerId;
    private Long restaurantId;

    @DateTimeFormat(iso = DATE_TIME)
    private OffsetDateTime dateFrom;
    @DateTimeFormat(iso = DATE_TIME)
    private OffsetDateTime dateTo;
}
