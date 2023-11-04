package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.assembliers.GroupModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.GroupModel;
import com.eskcti.algafoodapi.api.v1.openapi.UserGroupControllerOpenApi;
import com.eskcti.algafoodapi.domain.models.User;
import com.eskcti.algafoodapi.domain.services.GroupService;
import com.eskcti.algafoodapi.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/groups")
public class UserGroupController implements UserGroupControllerOpenApi {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupModelAssemblier modelAssemblier;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<GroupModel> list(@PathVariable Long userId) {
        User user = userService.find(userId);
        CollectionModel<GroupModel> collectionModel = modelAssemblier
                .toCollectionModel(user.getGroups())
                .removeLinks()
                .add(algaLinks.linkToGroupsByUser(userId))
                .add(algaLinks.linkToGroupAssociateByUser(userId, "associate"));

        collectionModel.getContent().forEach(groupModel -> {
            groupModel.add(
                    algaLinks.linkToGroupDisassociateByUser(userId, groupModel.getId(), "disassociate")
            );
        });

        return collectionModel;
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.associateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.disassociateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }
}
