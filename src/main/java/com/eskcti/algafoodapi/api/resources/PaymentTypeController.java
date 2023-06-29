package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.services.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/payment_types", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PaymentTypeController {
    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping
    public List<PaymentType> list() {
        return paymentTypeService.list();
    }
}
