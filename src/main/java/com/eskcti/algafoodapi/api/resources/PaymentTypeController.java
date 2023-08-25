package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.PaymentTypeInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.PaymentTypeModelAssemblier;
import com.eskcti.algafoodapi.api.model.PaymentTypeModel;
import com.eskcti.algafoodapi.api.model.input.PaymentTypeInput;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.services.PaymentTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity<List<PaymentTypeModel>> list() {
        List<PaymentType> paymentTypeList = paymentTypeService.list();

        List<PaymentTypeModel> paymentTypeModels = modelAssemblier.toCollectionModel(paymentTypeList);

        return ResponseEntity.ok()
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
//                .cacheControl(CacheControl.noCache())
//                .cacheControl(CacheControl.noStore())
                .body(paymentTypeModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentTypeModel> find(@PathVariable Long id) {
        PaymentType paymentType = paymentTypeService.find(id);
        PaymentTypeModel paymentTypeModel = modelAssemblier.toModel(paymentType);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(paymentTypeModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentTypeModel create(@RequestBody @Valid PaymentTypeInput paymentTypeInput) {
        PaymentType paymentType = inputDisassembler.toDomainObject(paymentTypeInput);
        return modelAssemblier.toModel(paymentTypeService.save(paymentType));
    }

    @PutMapping("/{id}")
    public PaymentTypeModel update(@PathVariable Long id, @RequestBody PaymentTypeInput paymentTypeInput) {
        PaymentType paymentTypeUpdate = paymentTypeService.find(id);
        inputDisassembler.copyToDomainObject(paymentTypeInput, paymentTypeUpdate);
        return modelAssemblier.toModel(paymentTypeService.save(paymentTypeUpdate));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paymentTypeService.remove(id);
    }
}
