package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.UserModel;
import com.eskcti.algafoodapi.api.resources.UserController;
import com.eskcti.algafoodapi.api.resources.UserGroupController;
import com.eskcti.algafoodapi.domain.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssemblier extends RepresentationModelAssemblerSupport<User, UserModel> {
    @Autowired
    private ModelMapper modelMapper;
    public UserModel toModel(User user) {
        UserModel userModel = createModelWithId(user.getId(), user);
        modelMapper.map(user, userModel);

        userModel.add(linkTo(methodOn(UserController.class).list())
                .withRel("users"));
        userModel.add(linkTo(methodOn(UserGroupController.class).list(user.getId()))
                .withRel("groups-user"));

        return userModel;
    }

    public UserModelAssemblier() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(UserController.class).withSelfRel());
    }

//    public List<UserModel> toCollectionModel(Collection<User> users) {
//        return users.stream()
//                .map(user -> toModel(user))
//                .collect(Collectors.toList());
//    }
}
