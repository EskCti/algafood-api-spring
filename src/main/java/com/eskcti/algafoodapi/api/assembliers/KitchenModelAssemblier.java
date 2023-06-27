package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.KitchenModel;
import com.eskcti.algafoodapi.api.model.RestaurantModel;
import com.eskcti.algafoodapi.api.model.input.KitchenInput;
import com.eskcti.algafoodapi.api.model.input.RestaurantInput;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KitchenModelAssemblier {
    public KitchenModel toModel(Kitchen kitchen) {
        KitchenModel kitchenModel = new KitchenModel();
        kitchenModel.setId(kitchen.getId());
        kitchenModel.setName(kitchen.getName());

        return kitchenModel;
    }

    public List<KitchenModel> toCollectionModel(List<Kitchen> kitchens) {
        return kitchens.stream()
                .map(kitchen -> toModel(kitchen))
                .collect(Collectors.toList());
    }
}
