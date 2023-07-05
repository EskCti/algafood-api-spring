package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.PermissionModelAssemblier;
import com.eskcti.algafoodapi.api.model.PermissionModel;
import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/groups/{groupId}/permissions")
public class GroupPermissionController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionModelAssemblier modelAssemblier;

    @GetMapping
    public List<PermissionModel> list(@PathVariable Long groupId) {
        Group group = groupService.find(groupId);
        return modelAssemblier.toCollectionModel(group.getPermissions());
    }
}
