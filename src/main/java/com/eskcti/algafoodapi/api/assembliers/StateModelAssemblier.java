package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.KitchenModel;
import com.eskcti.algafoodapi.api.model.StateModel;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.State;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateModelAssemblier {
    public StateModel toModel(State state) {
        StateModel stateModel = new StateModel();
        stateModel.setId(state.getId());
        stateModel.setName(state.getName());

        return stateModel;
    }

    public List<StateModel> toCollectionModel(List<State> states) {
        return states.stream()
                .map(state -> toModel(state))
                .collect(Collectors.toList());
    }
}
