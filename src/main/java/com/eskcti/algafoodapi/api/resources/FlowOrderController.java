package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.services.FlowOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderCode}")
public class FlowOrderController {
    @Autowired
    private FlowOrderService flowOrderService;
    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirm(@PathVariable String orderCode) {
        flowOrderService.confirm(orderCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delivery(@PathVariable String orderCode) {
        flowOrderService.delivery(orderCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancel(@PathVariable String orderCode) {
        flowOrderService.cancel(orderCode);
        return ResponseEntity.noContent().build();
    }
}
