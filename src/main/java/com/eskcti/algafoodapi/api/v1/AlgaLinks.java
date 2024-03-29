package com.eskcti.algafoodapi.api.v1;

import com.eskcti.algafoodapi.api.v1.resources.*;
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

    public Link linkToPaymentTypeDisassociateByRestaurant(Long restaurantId, Long paymentTypeId, String rel) {
        return linkTo(methodOn(RestaurantPaymentTypeController.class).disassociate(restaurantId, paymentTypeId)).withRel(rel);
    }

    public Link linkToPaymentTypeAssociateByRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantPaymentTypeController.class).associate(restaurantId, null)).withRel(rel);
    }

    public Link linkToResponsibleByRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantResponsibleController.class).list(restaurantId)).withRel(rel);
    }

    public Link linkToResponsibleDisassociateByRestaurant(Long restaurantId, Long responsibleId, String rel) {
        return linkTo(methodOn(RestaurantResponsibleController.class).disassociate(restaurantId, responsibleId)).withRel(rel);
    }

    public Link linkToResponsibleAssociateByRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantResponsibleController.class).associate(restaurantId, null)).withRel(rel);
    }

    public Link linkToProductsByRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .list(restaurantId, null))
                .withRel(rel);
    }

    public Link linkToProductByRestaurant(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .find(restaurantId, productId))
                .withRel(rel);
    }

    public Link linkToPhotoOfProductByRestaurant(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductPhotoController.class)
                .findPhoto(restaurantId, productId))
                .withRel(rel);
    }

    public Link linkToGroups(String rel) {
        return linkTo(methodOn(GroupController.class)
                .list())
                .withRel(rel);
    }

    public Link linkToGroup(Long groupId) {
        return linkTo(methodOn(GroupController.class)
                .find(groupId))
                .withSelfRel();
    }

    public Link linkToPermissionsByGrupo(Long groupId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .list(groupId))
                .withRel(rel);
    }

    public Link linkToPermissionDisassociateByGroup(Long groupId, Long permissionId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .disassociate(groupId, permissionId))
                .withRel(rel);
    }

    public Link linkToPermissionAssociateByGroup(Long groupId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .associate(groupId, null))
                .withRel(rel);
    }

    public Link linkToGroupsByUser(Long userId) {
        return linkTo(methodOn(UserGroupController.class).list(userId))
                .withRel("groups-user");
    }

    public Link linkToGroupDisassociateByUser(Long userId, Long groupId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .disassociate(userId, groupId))
                .withRel(rel);
    }

    public Link linkToGroupAssociateByUser(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .associate(userId, null))
                .withRel(rel);
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

    public Link linkToStatistics(String rel) {
        return linkTo(StatisticsController.class).withRel(rel);
    }

    public Link linkToStatisticsDailySales(String rel) {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dateFrom", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dateTo", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("timeOffset", TemplateVariable.VariableType.REQUEST_PARAM));

        String ordersUrl = linkTo(methodOn(StatisticsController.class)
                .DailySalesQuery(null, null)).toUri().toString();

        return Link.of(UriTemplate.of(ordersUrl, filterVariables), rel);
    }
}
