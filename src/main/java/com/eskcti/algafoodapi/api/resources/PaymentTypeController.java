package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.PaymentTypeModelAssemblier;
import com.eskcti.algafoodapi.api.model.PaymentTypeModel;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.services.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/payment_types", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PaymentTypeController {
    @Autowired
    private PaymentTypeService paymentTypeService;

    @Autowired
    private PaymentTypeModelAssemblier modelAssemblier;

    @GetMapping
    public List<PaymentType> list() {
        return paymentTypeService.list();
    }

    @GetMapping("/{id}")
    public PaymentTypeModel find(@PathVariable Long id) {
        return modelAssemblier.toModel(paymentTypeService.find(id));
    }


}
