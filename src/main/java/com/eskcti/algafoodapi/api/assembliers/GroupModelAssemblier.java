package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.GroupModel;
import com.eskcti.algafoodapi.api.resources.GroupController;
import com.eskcti.algafoodapi.domain.models.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GroupModelAssemblier extends RepresentationModelAssemblerSupport<Group, GroupModel> {
    @Autowired
    private ModelMapper modelMapper;
    public GroupModel toModel(Group group) {
        GroupModel groupModel = createModelWithId(group.getId(), group);
        modelMapper.map(group, groupModel);

        groupModel.add(linkTo(methodOn(GroupController.class).list())
                .withRel("groups"));

        return groupModel;
    }

    @Override
    public CollectionModel<GroupModel> toCollectionModel(Iterable<? extends Group> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(GroupController.class).withSelfRel());
    }
    public GroupModelAssemblier() {
        super(GroupController.class, GroupModel.class);
    }



//    public List<GroupModel> toCollectionModel(Collection<Group> groups) {
//        return groups.stream()
//                .map(group -> toModel(group))
//                .collect(Collectors.toList());
//    }
}
