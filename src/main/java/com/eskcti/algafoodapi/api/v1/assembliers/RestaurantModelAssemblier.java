package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.model.RestaurantModel;
import com.eskcti.algafoodapi.api.v1.resources.RestaurantController;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public RestaurantModelAssemblier() {
        super(RestaurantController.class, RestaurantModel.class);
    }
    public RestaurantModel toModel(Restaurant restaurant) {
        RestaurantModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantModel);

        if (algaSecurity.canConsultRestaurants()) {
            restaurantModel.add(algaLinks.linkToRestaurants());
        }

        if (algaSecurity.canManagerRegisterRestaurants()) {
            if (!restaurant.getActive()) {
                restaurantModel.add(algaLinks.linkToActivateByRestaurant(restaurant.getId(), "active"));
            }

            if (restaurant.getActive()) {
                restaurantModel.add(algaLinks.linkToDeactivateByRestaurant(restaurant.getId(), "deactivate"));
            }
        }

        if (algaSecurity.canManageRestaurantOperations(restaurant.getId())) {
            if (!restaurant.getOpen()) {
                restaurantModel.add(algaLinks.linkToOpeningByRestaurant(restaurant.getId(), "open"));
            }

            if (restaurant.getOpen()) {
                restaurantModel.add(algaLinks.linkToClosingByRestaurant(restaurant.getId(), "close"));
            }
        }

        if (algaSecurity.canConsultRestaurants()) {
            restaurantModel.add(algaLinks.linkToProductsByRestaurant(restaurant.getId(), "products"));
        }

        if (algaSecurity.canConsultKitchens()) {
            restaurantModel.getKitchen().add(algaLinks.linkToKitchen(restaurantModel.getKitchen().getId()));
        }

        if (algaSecurity.canConsultCities()) {
            if (restaurantModel.getAddress() != null) {
                restaurantModel.getAddress().getCity().add(algaLinks.linkToCity(restaurantModel.getAddress().getCity().getId()));
            }
        }

        if (algaSecurity.canConsultRestaurants()) {
            restaurantModel.add(algaLinks.linkToPaymentTypesByRestaurant(restaurant.getId(), "payment-types"));
        }

        if (algaSecurity.canManagerRegisterRestaurants()) {
            restaurantModel.add(algaLinks.linkToResponsibleByRestaurant(restaurant.getId(), "responsible"));
        }

        return restaurantModel;
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantModel> collectionModel =
                super.toCollectionModel(entities);

        if (algaSecurity.canConsultRestaurants()) {
            collectionModel
                    .add(linkTo(RestaurantController.class).withSelfRel());
        }

        return collectionModel;
    }

//    public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
//        return restaurants.stream()
//                .map(restaurant -> toModel(restaurant))
//                .collect(Collectors.toList());
//    }
}
