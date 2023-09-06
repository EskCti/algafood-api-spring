package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.UserModelAssemblier;
import com.eskcti.algafoodapi.api.model.UserModel;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/restaurants/{restaurantId}/responsible")
public class RestaurantResponsibleController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserModelAssemblier modelAssemblier;

    @GetMapping
    public CollectionModel<UserModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);
        return modelAssemblier.toCollectionModel(restaurant.getResponsible())
                .removeLinks()
                .add(linkTo(methodOn(RestaurantResponsibleController.class).list(restaurantId)).withSelfRel());
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);
    }
}
