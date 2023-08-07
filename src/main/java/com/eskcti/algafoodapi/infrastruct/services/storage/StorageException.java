package com.eskcti.algafoodapi.infrastruct.services.storage;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StorageException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
