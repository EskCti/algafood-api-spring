package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.model.PermissionModel;
import com.eskcti.algafoodapi.api.v1.resources.GroupPermissionController;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
import com.eskcti.algafoodapi.domain.models.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PermissionModelAssemblier extends RepresentationModelAssemblerSupport<Permission, PermissionModel> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public PermissionModelAssemblier() {
        super(GroupPermissionController.class, PermissionModel.class);
    }
    public PermissionModel toModel(Permission permission) {
        PermissionModel permissionModel = modelMapper.map(permission, PermissionModel.class);
        return permissionModel;
    }

    @Override
    public CollectionModel<PermissionModel> toCollectionModel(Iterable<? extends Permission> entities) {
        CollectionModel<PermissionModel> collectionModel
                = super.toCollectionModel(entities);
        if (algaSecurity.canConsultUsersGroupsPermissions()) {
            collectionModel
                    .add(linkTo(GroupPermissionController.class).withSelfRel());
        }
        return collectionModel;
    }

//    public List<PermissionModel> toCollectionModel(Collection<Permission> permissions) {
//        return permissions.stream()
//                .map(permission -> toModel(permission))
//                .collect(Collectors.toList());
//    }
}
