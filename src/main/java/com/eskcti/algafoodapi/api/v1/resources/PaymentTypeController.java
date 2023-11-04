package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.assembliers.PaymentTypeInputDisassembler;
import com.eskcti.algafoodapi.api.v1.assembliers.PaymentTypeModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.PaymentTypeModel;
import com.eskcti.algafoodapi.api.v1.model.input.PaymentTypeInput;
import com.eskcti.algafoodapi.api.v1.openapi.PaymentTypeControllerOpenApi;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.services.PaymentTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value="/payment_types", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PaymentTypeController implements PaymentTypeControllerOpenApi {
    @Autowired
    private PaymentTypeService paymentTypeService;

    @Autowired
    private PaymentTypeModelAssemblier modelAssemblier;

    @Autowired
    private PaymentTypeInputDisassembler inputDisassembler;

    @GetMapping
    public ResponseEntity<CollectionModel<PaymentTypeModel>> list(ServletWebRequest request) {
        String eTag = getEtag(request);
        if (eTag == null) return null;

        List<PaymentType> paymentTypeList = paymentTypeService.list();

        CollectionModel<PaymentTypeModel> paymentTypeModels = modelAssemblier.toCollectionModel(paymentTypeList);

        return ResponseEntity.ok()
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
//                .cacheControl(CacheControl.noCache())
//                .cacheControl(CacheControl.noStore())
                .eTag(eTag)
                .body(paymentTypeModels);
    }

    private String getEtag(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime updatedAt = paymentTypeService.getUpdatedAt();

        if (updatedAt != null) {
            eTag = String.valueOf(updatedAt.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }
        return eTag;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentTypeModel> find(ServletWebRequest request, @PathVariable Long id) {
        String eTag = getEtag(request);
        if (eTag == null) return null;

        PaymentType paymentType = paymentTypeService.find(id);
        PaymentTypeModel paymentTypeModel = modelAssemblier.toModel(paymentType);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
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
