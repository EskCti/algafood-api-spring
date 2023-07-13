package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.PermissionModelAssemblier;
import com.eskcti.algafoodapi.api.model.PermissionModel;
import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.services.GroupService;
import com.eskcti.algafoodapi.domain.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups/{groupId}/permissions")
public class GroupPermissionController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionModelAssemblier modelAssemblier;

    @GetMapping
    public List<PermissionModel> list(@PathVariable Long groupId) {
        Group group = groupService.find(groupId);
        return modelAssemblier.toCollectionModel(group.getPermissions());
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.associatePermission(groupId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.disassociatePermission(groupId, permissionId);
    }
}
