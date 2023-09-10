package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.AlgaLinks;
import com.eskcti.algafoodapi.api.assembliers.PaymentTypeModelAssemblier;
import com.eskcti.algafoodapi.api.model.PaymentTypeModel;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/payment_types", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class RestaurantPaymentTypeController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentTypeModelAssemblier modelAssemblier;
    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<PaymentTypeModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        CollectionModel<PaymentTypeModel> paymentTypeModels = modelAssemblier.toCollectionModel(restaurant.getPaymentTypes())
                .removeLinks()
                .add(algaLinks.linkToPaymentTypesByRestaurant(restaurantId))
                .add(
                        algaLinks.linkToPaymentTypeAssociateByRestaurant(restaurantId, "associate")
                );

        paymentTypeModels.getContent().forEach(paymentTypeModel -> {
            paymentTypeModel.add(
                    algaLinks.linkToPaymentTypeDisassociateByRestaurant(restaurantId, paymentTypeModel.getId(), "disassociate")
            );
        });

        return paymentTypeModels;
    }

    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentTypeId) {
        restaurantService.disassociatePaymentType(restaurantId, paymentTypeId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long paymentTypeId) {
        restaurantService.associatePaymentType(restaurantId, paymentTypeId);
        return ResponseEntity.noContent().build();
    }
}
