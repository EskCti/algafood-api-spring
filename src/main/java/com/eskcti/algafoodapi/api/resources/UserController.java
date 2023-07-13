package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.UserInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.UserModelAssemblier;
import com.eskcti.algafoodapi.api.model.UserModel;
import com.eskcti.algafoodapi.api.model.input.UserInput;
import com.eskcti.algafoodapi.api.model.input.UserUpdate;
import com.eskcti.algafoodapi.api.model.input.UserUpdatePassword;
import com.eskcti.algafoodapi.domain.models.User;
import com.eskcti.algafoodapi.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserModelAssemblier modelAssemblier;
    @Autowired
    private UserInputDisassembler inputDisassembler;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> list() {
        return modelAssemblier.toCollectionModel(userService.list());
    }

    @GetMapping("/{id}")
    public UserModel find(@PathVariable Long id) {
        return modelAssemblier.toModel(userService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel insert(@RequestBody @Valid UserInput userInput) {
        User user = inputDisassembler.toDomainObject(userInput);
        return modelAssemblier.toModel(userService.save(user));
    }

    @PutMapping("/{id}")
    public UserModel update(@PathVariable Long id, @RequestBody @Valid UserUpdate userUpdate) {
        User userActual = userService.find(id);
        inputDisassembler.copyToDomainObject(userUpdate, userActual);

        return modelAssemblier.toModel(userService.save(userActual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long id, @RequestBody @Valid UserUpdatePassword userUpdatePassword) {
        userService.updatePassword(id, userUpdatePassword.getCurrentPassword(), userUpdatePassword.getNewPassword());
    }
}
