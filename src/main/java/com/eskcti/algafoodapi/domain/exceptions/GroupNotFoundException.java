package com.eskcti.algafoodapi.domain.exceptions;

public class GroupNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(Long groupId) {
        this(String.format("Not found Group with id %d", groupId));
    }
}
