package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.AlgaLinks;
import com.eskcti.algafoodapi.api.model.RestaurantModel;
import com.eskcti.algafoodapi.api.resources.RestaurantController;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class RestaurantModelAssemblier  extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestaurantModelAssemblier() {
        super(RestaurantController.class, RestaurantModel.class);
    }
    public RestaurantModel toModel(Restaurant restaurant) {
        RestaurantModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantModel);

        restaurantModel.add(algaLinks.linkToRestaurants());
        restaurantModel.add(algaLinks.linkToPaymentTypesByRestaurant(restaurant.getId(), "payment-types"));
        restaurantModel.add(algaLinks.linkToResponsibleByRestaurant(restaurant.getId(), "responsible"));

        restaurantModel.add(algaLinks.linkToProductsByRestaurant(restaurant.getId(), "products"));

        if (!restaurant.getActive()) {
            restaurantModel.add(algaLinks.linkToActivateByRestaurant(restaurant.getId(), "active"));
        }

        if (restaurant.getActive()) {
            restaurantModel.add(algaLinks.linkToDeactivateByRestaurant(restaurant.getId(), "deactivate"));
        }

        if (!restaurant.getOpen()) {
            restaurantModel.add(algaLinks.linkToOpeningByRestaurant(restaurant.getId(), "open"));
        }

        if (restaurant.getOpen()) {
            restaurantModel.add(algaLinks.linkToClosingByRestaurant(restaurant.getId(), "close"));
        }

        restaurantModel.getKitchen().add(algaLinks.linkToKitchen(restaurantModel.getKitchen().getId()));

        if (restaurantModel.getAddress() != null) {
            restaurantModel.getAddress().getCity().add(algaLinks.linkToCity(restaurantModel.getAddress().getCity().getId()));
        }

        return restaurantModel;
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(RestaurantController.class).withSelfRel());
    }


//    public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
//        return restaurants.stream()
//                .map(restaurant -> toModel(restaurant))
//                .collect(Collectors.toList());
//    }
}
