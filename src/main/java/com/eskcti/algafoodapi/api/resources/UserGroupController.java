package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.GroupModelAssemblier;
import com.eskcti.algafoodapi.api.assembliers.UserModelAssemblier;
import com.eskcti.algafoodapi.api.model.GroupModel;
import com.eskcti.algafoodapi.api.model.UserModel;
import com.eskcti.algafoodapi.domain.models.User;
import com.eskcti.algafoodapi.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/groups")
public class UserGroupController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupModelAssemblier modelAssemblier;

    @GetMapping
    public List<GroupModel> list(@PathVariable Long userId) {
        User user = userService.find(userId);
        return modelAssemblier.toCollectionModel(user.getGroups());
    }
}
