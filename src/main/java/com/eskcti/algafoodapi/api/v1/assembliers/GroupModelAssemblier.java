package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.model.GroupModel;
import com.eskcti.algafoodapi.api.v1.resources.GroupController;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public GroupModel toModel(Group group) {
        GroupModel groupModel = createModelWithId(group.getId(), group);
        modelMapper.map(group, groupModel);

        if (algaSecurity.canConsultUsersGroupsPermissions()) {
            groupModel.add(algaLinks.linkToGroups("groups"));
            groupModel.add(algaLinks.linkToPermissionsByGrupo(group.getId(), "permissions"));
        }

        return groupModel;
    }

    @Override
    public CollectionModel<GroupModel> toCollectionModel(Iterable<? extends Group> entities) {
        CollectionModel<GroupModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultUsersGroupsPermissions()) {
            collectionModel
                    .add(linkTo(GroupController.class).withSelfRel());
        }
        return collectionModel;
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
