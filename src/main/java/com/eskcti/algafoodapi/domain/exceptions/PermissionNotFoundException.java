package com.eskcti.algafoodapi.domain.exceptions;

public class PermissionNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long permissionId) {
        this(String.format("Not found Permission with id %d", permissionId));
    }
}
