package com.eskcti.algafoodapi.infrastruct.services.email;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
