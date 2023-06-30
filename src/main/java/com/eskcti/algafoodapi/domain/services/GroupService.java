package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public List<Group> list() {
        return groupRepository.findAll();
    }
}
