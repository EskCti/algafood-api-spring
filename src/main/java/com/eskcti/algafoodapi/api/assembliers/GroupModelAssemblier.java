package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.AlgaLinks;
import com.eskcti.algafoodapi.api.model.GroupModel;
import com.eskcti.algafoodapi.api.resources.GroupController;
import com.eskcti.algafoodapi.domain.models.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class GroupModelAssemblier extends RepresentationModelAssemblerSupport<Group, GroupModel> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;
    public GroupModel toModel(Group group) {
        GroupModel groupModel = createModelWithId(group.getId(), group);
        modelMapper.map(group, groupModel);

        groupModel.add(algaLinks.linkToGroups("groups"));
        groupModel.add(algaLinks.linkToPermissionsByGrupo(group.getId(), "permissions"));

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
