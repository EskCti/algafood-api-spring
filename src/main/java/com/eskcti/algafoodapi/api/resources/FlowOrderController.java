package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.services.FlowOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderId}")
public class FlowOrderController {
    @Autowired
    private FlowOrderService flowOrderService;
    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable Long orderId) {
        flowOrderService.confirm(orderId);
    }
}
