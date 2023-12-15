package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.PermissionNotFoundException;
import com.eskcti.algafoodapi.domain.models.Permission;
import com.eskcti.algafoodapi.domain.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public Permission find(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));
    }
}
