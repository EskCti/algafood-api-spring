package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.openapi.FlowOrderControllerOpenApi;
import com.eskcti.algafoodapi.core.security.CheckSecutiry;
import com.eskcti.algafoodapi.domain.services.FlowOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/orders/{orderCode}")
public class FlowOrderController implements FlowOrderControllerOpenApi {


    @Autowired
    private FlowOrderService flowOrderService;

    @CheckSecutiry.Orders.CanManager
    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirm(@PathVariable String orderCode) {
        flowOrderService.confirm(orderCode);
        return ResponseEntity.noContent().build();
    }

    @CheckSecutiry.Orders.CanManager
    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delivery(@PathVariable String orderCode) {
        flowOrderService.delivery(orderCode);
        return ResponseEntity.noContent().build();
    }

    @CheckSecutiry.Orders.CanManager
    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancel(@PathVariable String orderCode) {
        flowOrderService.cancel(orderCode);
        return ResponseEntity.noContent().build();
    }
}
