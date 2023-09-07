package com.eskcti.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "paymentsType")
@Getter
@Setter
public class PaymentTypeModel extends RepresentationModel<PaymentTypeModel> {
    private Long id;
    private String description;
}
