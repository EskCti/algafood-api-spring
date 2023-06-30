package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.GroupInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.GroupModelAssemblier;
import com.eskcti.algafoodapi.api.model.GroupModel;
import com.eskcti.algafoodapi.api.model.input.GroupInput;
import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.services.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    GroupModelAssemblier modelAssemblier;

    @Autowired
    GroupInputDisassembler inputDisassembler;

    @GetMapping
    public List<GroupModel> list() {
        return modelAssemblier.toCollectionModel(groupService.list());
    }

    @GetMapping("/{id}")
    public GroupModel find(@PathVariable Long id) {
        return modelAssemblier.toModel(groupService.find(id));
    }

    @PostMapping
    public GroupModel insert(@RequestBody @Valid GroupInput groupInput) {
        Group group =  inputDisassembler.toDomainObject(groupInput);
        return modelAssemblier.toModel(groupService.save(group));
    }

    @PutMapping("/{id}")
    public GroupModel update(@PathVariable Long id, @RequestBody @Valid GroupInput groupInput) {
        Group groupUpdate = groupService.find(id);
        inputDisassembler.copyToDomainObject(groupInput, groupUpdate);
        return modelAssemblier.toModel(groupService.save(groupUpdate));
    }
}
