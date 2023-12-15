package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.assembliers.PaymentTypeModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.PaymentTypeModel;
import com.eskcti.algafoodapi.api.v1.openapi.RestaurantPaymentTypeControllerOpenApi;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
import com.eskcti.algafoodapi.core.security.CheckSecutiry;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/restaurants/{restaurantId}/payment_types", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class RestaurantPaymentTypeController implements RestaurantPaymentTypeControllerOpenApi {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentTypeModelAssemblier modelAssemblier;
    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @CheckSecutiry.Restaurants.CanConsult
    @GetMapping
    public CollectionModel<PaymentTypeModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        CollectionModel<PaymentTypeModel> paymentTypeModels = modelAssemblier.toCollectionModel(restaurant.getPaymentTypes())
                .removeLinks();

        paymentTypeModels
                .add(algaLinks.linkToPaymentTypesByRestaurant(restaurantId));

        if (algaSecurity.canManageRestaurantOperations(restaurantId)) {
            paymentTypeModels
                    .add(algaLinks.linkToPaymentTypeAssociateByRestaurant(restaurantId, "associate"));

            paymentTypeModels.getContent().forEach(paymentTypeModel -> {
                paymentTypeModel.add(
                        algaLinks.linkToPaymentTypeDisassociateByRestaurant(restaurantId, paymentTypeModel.getId(), "disassociate")
                );
            });
        }

        return paymentTypeModels;
    }

    @CheckSecutiry.Restaurants.CanManager
    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentTypeId) {
        restaurantService.disassociatePaymentType(restaurantId, paymentTypeId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecutiry.Restaurants.CanManager
    @PutMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long paymentTypeId) {
        restaurantService.associatePaymentType(restaurantId, paymentTypeId);
        return ResponseEntity.noContent().build();
    }
}
