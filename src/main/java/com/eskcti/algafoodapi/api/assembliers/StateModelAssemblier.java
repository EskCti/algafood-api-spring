package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.AlgaLinks;
import com.eskcti.algafoodapi.api.model.StateModel;
import com.eskcti.algafoodapi.api.resources.StateController;
import com.eskcti.algafoodapi.domain.models.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class StateModelAssemblier extends RepresentationModelAssemblerSupport<State, StateModel> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public StateModelAssemblier() {
        super(StateController.class, StateModel.class);
    }

    public StateModel toModel(State state) {
        StateModel stateModel = createModelWithId(state.getId(), state);
        modelMapper.map(state, stateModel);

        stateModel.add(algaLinks.linkToStates());

        return stateModel;
    }

    @Override
    public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(StateController.class).withSelfRel());
    }

//    public List<StateModel> toCollectionModel(List<State> states) {
//        return states.stream()
//                .map(state -> toModel(state))
//                .collect(Collectors.toList());
//    }
}
