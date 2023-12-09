package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.model.PaymentTypeModel;
import com.eskcti.algafoodapi.api.v1.resources.PaymentTypeController;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PaymentTypeModelAssemblier extends RepresentationModelAssemblerSupport<PaymentType, PaymentTypeModel> {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public PaymentTypeModelAssemblier() {
        super(PaymentTypeController.class, PaymentTypeModel.class);
    }
    public PaymentTypeModel toModel(PaymentType paymentType) {
        PaymentTypeModel paymentTypeModel = createModelWithId(paymentType.getId(), paymentType);
        modelMapper.map(paymentType, paymentTypeModel);

        if (algaSecurity.canConsultPaymentsType()) {
            paymentTypeModel.add(algaLinks.linkToPaymentTypes());
        }

        return paymentTypeModel;
    }

    @Override
    public CollectionModel<PaymentTypeModel> toCollectionModel(Iterable<? extends PaymentType> entities) {
        CollectionModel<PaymentTypeModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultPaymentsType()) {
            collectionModel
                    .add(linkTo(PaymentTypeController.class).withSelfRel());
        }
        return collectionModel;
    }

//    public List<PaymentTypeModel> toCollectionModel(Collection<PaymentType> paymentTypes) {
//        return paymentTypes.stream()
//                .map(paymentType -> toModel(paymentType))
//                .collect(Collectors.toList());
//    }
}
