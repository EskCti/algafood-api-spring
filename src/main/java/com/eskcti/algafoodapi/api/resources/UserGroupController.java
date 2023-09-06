package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.GroupModelAssemblier;
import com.eskcti.algafoodapi.api.model.GroupModel;
import com.eskcti.algafoodapi.domain.models.User;
import com.eskcti.algafoodapi.domain.services.GroupService;
import com.eskcti.algafoodapi.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/groups")
public class UserGroupController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupModelAssemblier modelAssemblier;

    @GetMapping
    public CollectionModel<GroupModel> list(@PathVariable Long userId) {
        User user = userService.find(userId);
        return modelAssemblier.toCollectionModel(user.getGroups());
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.associateGroup(userId, groupId);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.disassociateGroup(userId, groupId);
    }
}
