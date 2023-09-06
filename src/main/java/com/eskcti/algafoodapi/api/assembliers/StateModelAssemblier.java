package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.StateModel;
import com.eskcti.algafoodapi.api.model.UserModel;
import com.eskcti.algafoodapi.api.resources.StateController;
import com.eskcti.algafoodapi.api.resources.UserController;
import com.eskcti.algafoodapi.domain.models.State;
import com.eskcti.algafoodapi.domain.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StateModelAssemblier extends RepresentationModelAssemblerSupport<State, StateModel> {
    @Autowired
    private ModelMapper modelMapper;

    public StateModelAssemblier() {
        super(StateController.class, StateModel.class);
    }

    public StateModel toModel(State state) {
        StateModel stateModel = createModelWithId(state.getId(), state);
        modelMapper.map(state, stateModel);

        stateModel.add(linkTo(methodOn(StateController.class).list())
                .withRel("states"));

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
