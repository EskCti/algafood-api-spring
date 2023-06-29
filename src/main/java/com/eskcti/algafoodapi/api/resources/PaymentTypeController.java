package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.PaymentTypeInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.PaymentTypeModelAssemblier;
import com.eskcti.algafoodapi.api.model.PaymentTypeModel;
import com.eskcti.algafoodapi.api.model.input.PaymentTypeInput;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.services.PaymentTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/payment_types", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PaymentTypeController {
    @Autowired
    private PaymentTypeService paymentTypeService;

    @Autowired
    private PaymentTypeModelAssemblier modelAssemblier;

    @Autowired
    private PaymentTypeInputDisassembler inputDisassembler;

    @GetMapping
    public List<PaymentType> list() {
        return paymentTypeService.list();
    }

    @GetMapping("/{id}")
    public PaymentTypeModel find(@PathVariable Long id) {
        return modelAssemblier.toModel(paymentTypeService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentTypeModel create(@RequestBody @Valid PaymentTypeInput paymentTypeInput) {
        PaymentType paymentType = inputDisassembler.toDomainObject(paymentTypeInput);
        return modelAssemblier.toModel(paymentTypeService.save(paymentType));
    }
}
