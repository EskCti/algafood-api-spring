package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Permission;

import java.util.List;

public interface PermissionRepository {
    List<Permission> list();
    Permission find(Long id);
    Permission save(Permission permission);
    void remove(Permission permission);
}
