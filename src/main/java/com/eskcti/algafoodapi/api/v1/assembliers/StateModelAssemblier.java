package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.model.StateModel;
import com.eskcti.algafoodapi.api.v1.resources.StateController;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public StateModelAssemblier() {
        super(StateController.class, StateModel.class);
    }

    public StateModel toModel(State state) {
        StateModel stateModel = createModelWithId(state.getId(), state);
        modelMapper.map(state, stateModel);

        if (algaSecurity.canConsultStates()) {
            stateModel.add(algaLinks.linkToStates());
        }

        return stateModel;
    }

    @Override
    public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities) {
        CollectionModel<StateModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultStates()) {
            collectionModel
                    .add(linkTo(StateController.class).withSelfRel());
        }
        return collectionModel;
    }

//    public List<StateModel> toCollectionModel(List<State> states) {
//        return states.stream()
//                .map(state -> toModel(state))
//                .collect(Collectors.toList());
//    }
}
