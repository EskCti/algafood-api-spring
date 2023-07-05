package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.GroupNotFoundException;
import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.models.Permission;
import com.eskcti.algafoodapi.domain.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService {
    public static final String GROUP_NOT_REMOVED_IN_USE = "Group with id %d not removed in use ";
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PermissionService permissionService;

    public List<Group> list() {
        return groupRepository.findAll();
    }

    public Group find(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
        return group;
    }

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public void remove(Long id) {
        try {
            Group group = find(id);
            groupRepository.delete(group);
            groupRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(String.format(GROUP_NOT_REMOVED_IN_USE, id));
        }
    }

    @Transactional
    public void associatePermission(Long groupId, Long permissionId) {
        Group group = find(groupId);
        Permission permission = permissionService.find(permissionId);

        group.associatePermission(permission);
    }
}
