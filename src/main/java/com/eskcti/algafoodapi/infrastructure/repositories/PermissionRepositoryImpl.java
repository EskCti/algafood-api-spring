package com.eskcti.algafoodapi.infrastructure.repositories;

import com.eskcti.algafoodapi.domain.models.Permission;
import com.eskcti.algafoodapi.domain.repositories.PermissionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permission> list() {
        return manager.createQuery("from Permission", Permission.class)
                .getResultList();
    }

    @Override
    public Permission find(Long id) {
        return manager.find(Permission.class, id);
    }

    @Override
    @Transactional
    public Permission save(Permission permission) {
        return manager.merge(permission);
    }

    @Override
    @Transactional
    public void remove(Permission permission) {
        permission = find(permission.getId());
        manager.remove(permission);
    }
}
