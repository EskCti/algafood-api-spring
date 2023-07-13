package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.services.FlowOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderCode}")
public class FlowOrderController {
    @Autowired
    private FlowOrderService flowOrderService;
    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String orderCode) {
        flowOrderService.confirm(orderCode);
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delivery(@PathVariable String orderCode) {
        flowOrderService.delivery(orderCode);
    }

    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String orderCode) {
        flowOrderService.cancel(orderCode);
    }
}
