package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.PaymentTypeModel;
import com.eskcti.algafoodapi.api.model.StateModel;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.models.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentTypeModelAssemblier {
    @Autowired
    private ModelMapper modelMapper;
    public PaymentTypeModel toModel(PaymentType paymentType) {
        return modelMapper.map(paymentType, PaymentTypeModel.class);
    }

    public List<PaymentTypeModel> toCollectionModel(List<PaymentType> paymentTypes) {
        return paymentTypes.stream()
                .map(paymentType -> toModel(paymentType))
                .collect(Collectors.toList());
    }
}
