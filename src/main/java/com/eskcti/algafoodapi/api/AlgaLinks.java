package com.eskcti.algafoodapi.api;

import com.eskcti.algafoodapi.api.resources.OrderController;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.TemplateVariable.VariableType.REQUEST_PARAM;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class AlgaLinks {

    public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
            new TemplateVariable("size", REQUEST_PARAM),
            new TemplateVariable("page", REQUEST_PARAM),
            new TemplateVariable("sort", REQUEST_PARAM)
    );

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
}
