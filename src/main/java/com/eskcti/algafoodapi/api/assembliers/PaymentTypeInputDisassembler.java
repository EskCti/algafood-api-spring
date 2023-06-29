package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.input.PaymentTypeInput;
import com.eskcti.algafoodapi.api.model.input.StateInput;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.models.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentTypeInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public PaymentType toDomainObject(PaymentTypeInput paymentTypeInput) {
        return modelMapper.map(paymentTypeInput, PaymentType.class);
    }

    public void copyToDomainObject(PaymentTypeInput paymentTypeInput, PaymentType paymentType) {
        modelMapper.map(paymentTypeInput, paymentType);
    }
}
