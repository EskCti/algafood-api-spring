package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.assembliers.GroupInputDisassembler;
import com.eskcti.algafoodapi.api.v1.assembliers.GroupModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.GroupModel;
import com.eskcti.algafoodapi.api.v1.model.input.GroupInput;
import com.eskcti.algafoodapi.api.v1.openapi.GroupControllerOpenApi;
import com.eskcti.algafoodapi.core.security.CheckSecutiry;
import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.services.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/groups", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class GroupController implements GroupControllerOpenApi {
    @Autowired
    private GroupService groupService;

    @Autowired
    GroupModelAssemblier modelAssemblier;

    @Autowired
    GroupInputDisassembler inputDisassembler;

    @CheckSecutiry.UsersGroupsPermissions.CanConsult
    @GetMapping
    public CollectionModel<GroupModel> list() {
        return modelAssemblier.toCollectionModel(groupService.list());
    }

    @CheckSecutiry.UsersGroupsPermissions.CanConsult
    @GetMapping("/{id}")
    public GroupModel find(@PathVariable Long id) {
        return modelAssemblier.toModel(groupService.find(id));
    }

    @CheckSecutiry.UsersGroupsPermissions.CanEdit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupModel insert(@RequestBody @Valid GroupInput groupInput) {
        Group group =  inputDisassembler.toDomainObject(groupInput);
        return modelAssemblier.toModel(groupService.save(group));
    }

    @CheckSecutiry.UsersGroupsPermissions.CanEdit
    @PutMapping("/{id}")
    public GroupModel update(@PathVariable Long id, @RequestBody @Valid GroupInput groupInput) {
        Group groupUpdate = groupService.find(id);
        inputDisassembler.copyToDomainObject(groupInput, groupUpdate);
        return modelAssemblier.toModel(groupService.save(groupUpdate));
    }

    @CheckSecutiry.UsersGroupsPermissions.CanEdit
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        groupService.remove(id);
    }
}
