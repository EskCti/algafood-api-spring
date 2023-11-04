package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.assembliers.UserModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.UserModel;
import com.eskcti.algafoodapi.api.v1.openapi.RestaurantResponsibleControllerOpenApi;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/restaurants/{restaurantId}/responsible")
public class RestaurantResponsibleController implements RestaurantResponsibleControllerOpenApi {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserModelAssemblier modelAssemblier;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<UserModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);
        CollectionModel<UserModel> collectionModel = modelAssemblier.toCollectionModel(restaurant.getResponsible())
                .removeLinks()
                .add(algaLinks.linkToResponsibleByRestaurant(restaurantId, "self"))
                .add(algaLinks.linkToResponsibleAssociateByRestaurant(restaurantId, "associate"));

        collectionModel.getContent().forEach(userModel -> {
            userModel.add(algaLinks.linkToResponsibleDisassociateByRestaurant(restaurantId, userModel.getId(), "disassociate"));
        });
        return collectionModel;
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }
}
