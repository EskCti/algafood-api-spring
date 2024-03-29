package com.eskcti.algafoodapi.api.v2.assembliers;

import com.eskcti.algafoodapi.api.v2.model.input.KitchenInputV2;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenInputDisassemblerV2 {
    @Autowired
    private ModelMapper modelMapper;

    public Kitchen toDomainObject(KitchenInputV2 kitchenInput) {
        return modelMapper.map(kitchenInput, Kitchen.class);
    }

    public void copyToDomainObject(KitchenInputV2 kitchenInput, Kitchen kitchen) {
        modelMapper.map(kitchenInput, kitchen);
    }

}
