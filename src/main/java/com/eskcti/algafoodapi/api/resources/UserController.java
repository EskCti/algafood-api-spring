package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.UserModelAssemblier;
import com.eskcti.algafoodapi.api.model.UserModel;
import com.eskcti.algafoodapi.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserModelAssemblier modelAssemblier;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> list() {
        return modelAssemblier.toCollectionModel(userService.list());
    }
}
