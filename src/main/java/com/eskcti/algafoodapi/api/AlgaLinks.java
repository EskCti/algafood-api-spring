package com.eskcti.algafoodapi.api;

import com.eskcti.algafoodapi.api.resources.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.TemplateVariable.VariableType.REQUEST_PARAM;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgaLinks {

    public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
            new TemplateVariable("size", REQUEST_PARAM),
            new TemplateVariable("page", REQUEST_PARAM),
            new TemplateVariable("sort", REQUEST_PARAM)
    );

    public Link linkToOrderConfirm(String orderCode, String rel) {
        return linkTo(methodOn(FlowOrderController.class).confirm(orderCode)).withRel(rel);
    }

    public Link linkToOrderCancel(String orderCode, String rel) {
        return linkTo(methodOn(FlowOrderController.class).cancel(orderCode)).withRel(rel);
    }

    public Link linkToOrderDelivery(String orderCode, String rel) {
        return linkTo(methodOn(FlowOrderController.class).delivery(orderCode)).withRel(rel);
    }

    public Link linkToOrders() {

        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("code", REQUEST_PARAM),
                new TemplateVariable("restaurant.id", REQUEST_PARAM),
                new TemplateVariable("nameRestaurant", REQUEST_PARAM),
                new TemplateVariable("nameCustomer", REQUEST_PARAM),
                new TemplateVariable("subtotal", REQUEST_PARAM),
                new TemplateVariable("shippingFee", REQUEST_PARAM),
                new TemplateVariable("valueTotal", REQUEST_PARAM)
        );

        String orderUri = linkTo(OrderController.class).toUri().toString();

        return Link.of(
                UriTemplate.of(orderUri, PAGE_VARIABLES.concat(filterVariables)), LinkRelation.of("orders")
        );
    }

    public Link linkToRestaurant(Long restaurantId) {
        return linkTo(methodOn(RestaurantController.class)
                .find(restaurantId)).withSelfRel();
    }

    public Link linkToRestaurants() {
        return linkTo(methodOn(RestaurantController.class).list())
                .withRel("restaurants");
    }

    public Link linkToCustomer(Long customerId) {
        return linkTo(methodOn(UserController.class)
                .find(customerId)).withSelfRel();
    }

    public Link linkToPaymentTypesByRestaurant(Long restaurantId, String rel) {
       return linkTo(methodOn(RestaurantPaymentTypeController.class).list(restaurantId)).withRel(rel);
    }

    public Link linkToPaymentTypesByRestaurant(Long restaurantId) {
        return linkTo(methodOn(RestaurantPaymentTypeController.class).list(restaurantId)).withSelfRel();
    }

    public Link linkToResponsibleByRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantResponsibleController.class).list(restaurantId)).withRel(rel);
    }

    public Link linkToActivateByRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class).activate(restaurantId)).withRel(rel);
    }

    public Link linkToDeactivateByRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class).deactivate(restaurantId)).withRel(rel);
    }

    public Link linkToOpeningByRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class).opening(restaurantId)).withRel(rel);
    }

    public Link linkToClosingByRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class).closing(restaurantId)).withRel(rel);
    }

    public Link linkToPaymentType(Long paymentoTypeId) {
        return linkTo(methodOn(PaymentTypeController.class)
                .find(null, paymentoTypeId)).withSelfRel();
    }

    public Link linkToPaymentTypes() {
        return linkTo(methodOn(PaymentTypeController.class)
                .list(null)).withSelfRel();
    }

    public Link linkToCity(Long cityId) {
        return linkTo(methodOn(CityController.class)
                .find(cityId)).withSelfRel();
    }

    public Link linkToCities() {
        return linkTo(methodOn(CityController.class).list())
                .withRel("cities");
    }

    public Link linkToState(Long stateId) {
        return linkTo(methodOn(StateController.class)
                .find(stateId)).withSelfRel();
    }

    public Link linkToStates() {
        return linkTo(methodOn(StateController.class).list())
                .withRel("states");
    }

    public Link linkToItemOrder(Long restaurantId, Long productId) {
        return linkTo(methodOn(RestaurantProductController.class)
                .find(restaurantId, productId))
                .withRel("product");
    }

    public Link linkToKitchen(Long kitchenId) {
        return linkTo(methodOn(KitchenController.class)
                .find(kitchenId)).withSelfRel();
    }
    public Link linkToKitchens() {
        return linkTo(KitchenController.class)
                .withRel("kitchens");
    }

    public Link linkToUsers() {
        return linkTo(methodOn(UserController.class).list())
                .withRel("users");
    }

    public Link linkToGroupUsers(Long userId) {
        return linkTo(methodOn(UserGroupController.class).list(userId))
                .withRel("groups-user");
    }
}
