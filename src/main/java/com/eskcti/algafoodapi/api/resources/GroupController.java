package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.GroupModelAssemblier;
import com.eskcti.algafoodapi.api.model.GroupModel;
import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/groups", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    GroupModelAssemblier modelAssemblier;

    @GetMapping
    public List<GroupModel> list() {
        return modelAssemblier.toCollectionModel(groupService.list());
    }

    @GetMapping("/{id}")
    public GroupModel find(@PathVariable Long id) {
        return modelAssemblier.toModel(groupService.find(id));
    }
}
