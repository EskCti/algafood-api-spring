package com.eskcti.algafoodapi.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "paymentsType")
@Schema(description = "Representa uma forma de pagamento")
@Getter
@Setter
public class PaymentTypeModel extends RepresentationModel<PaymentTypeModel> {
    @Schema(description = "ID da forma de pagamento", example = "1")
    private Long id;
    @Schema(example = "Cheque")
    private String description;
}
