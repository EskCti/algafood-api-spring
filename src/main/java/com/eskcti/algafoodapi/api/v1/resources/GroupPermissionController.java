package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.assembliers.PermissionModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.PermissionModel;
import com.eskcti.algafoodapi.api.v1.openapi.GroupPermissionControllerOpenApi;
import com.eskcti.algafoodapi.core.security.CheckSecutiry;
import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.services.GroupService;
import com.eskcti.algafoodapi.domain.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/groups/{groupId}/permissions")
public class GroupPermissionController implements GroupPermissionControllerOpenApi {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionModelAssemblier modelAssemblier;

    @Autowired
    private AlgaLinks algaLinks;

    @CheckSecutiry.UsersGroupsPermissions.CanConsult
    @GetMapping
    public CollectionModel<PermissionModel> list(@PathVariable Long groupId) {
        Group group = groupService.find(groupId);
        CollectionModel<PermissionModel> collectionModel = modelAssemblier
                .toCollectionModel(group.getPermissions())
                .removeLinks()
                .add(algaLinks.linkToPermissionsByGrupo(groupId, "permissions"))
                .add(algaLinks.linkToPermissionAssociateByGroup(groupId, "associate"));

        collectionModel.getContent().forEach(permissionModel -> {
            permissionModel.add(
                    algaLinks.linkToPermissionDisassociateByGroup(groupId, permissionModel.getId(), "disassociate")
            );
        });

        return collectionModel;
    }

    @CheckSecutiry.UsersGroupsPermissions.CanEdit
    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.associatePermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecutiry.UsersGroupsPermissions.CanEdit
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.disassociatePermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }
}
