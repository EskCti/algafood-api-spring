package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.assembliers.UserInputDisassembler;
import com.eskcti.algafoodapi.api.v1.assembliers.UserModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.UserModel;
import com.eskcti.algafoodapi.api.v1.model.input.UserInput;
import com.eskcti.algafoodapi.api.v1.model.input.UserUpdate;
import com.eskcti.algafoodapi.api.v1.model.input.UserUpdatePassword;
import com.eskcti.algafoodapi.api.v1.openapi.UserControllerOpenApi;
import com.eskcti.algafoodapi.core.security.CheckSecutiry;
import com.eskcti.algafoodapi.domain.models.User;
import com.eskcti.algafoodapi.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController implements UserControllerOpenApi {
    @Autowired
    private UserModelAssemblier modelAssemblier;
    @Autowired
    private UserInputDisassembler inputDisassembler;
    @Autowired
    private UserService userService;

    @CheckSecutiry.UsersGroupsPermissions.CanConsult
    @GetMapping
    public CollectionModel<UserModel> list() {
        return modelAssemblier.toCollectionModel(userService.list());
    }

    @CheckSecutiry.UsersGroupsPermissions.CanConsult
    @GetMapping("/{id}")
    public UserModel find(@PathVariable Long id) {
        return modelAssemblier.toModel(userService.find(id));
    }

    @CheckSecutiry.UsersGroupsPermissions.CanEdit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel insert(@RequestBody @Valid UserInput userInput) {
        User user = inputDisassembler.toDomainObject(userInput);
        return modelAssemblier.toModel(userService.save(user));
    }

    @CheckSecutiry.UsersGroupsPermissions.CanUpdateUser
    @PutMapping("/{id}")
    public UserModel update(@PathVariable Long id, @RequestBody @Valid UserUpdate userUpdate) {
        User userActual = userService.find(id);
        inputDisassembler.copyToDomainObject(userUpdate, userActual);

        return modelAssemblier.toModel(userService.save(userActual));
    }

    @CheckSecutiry.UsersGroupsPermissions.CanEdit
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @CheckSecutiry.UsersGroupsPermissions.CanUpdateOwnPassword
    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long id, @RequestBody @Valid UserUpdatePassword userUpdatePassword) {
        userService.updatePassword(id, userUpdatePassword.getCurrentPassword(), userUpdatePassword.getNewPassword());
    }
}
