package com.eskcti.algafoodapi.api.exceptionhandler;

import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String END_USER_GENERIC_ERROR_MESSAGE = "An unexpected internal system error has occurred." +
            " Try again and if the problem persists, contact your system administrator.";


    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ProblemType problemType = ProblemType.DATA_INVALID;
        String detail = "One or more fields are invalid. Fill in correctly and try again.";

        BindingResult bindingResult = ex.getBindingResult();

        List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder((HttpStatus) status, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String detail = END_USER_GENERIC_ERROR_MESSAGE;

        ex.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = String.format("The resource %s you tried to access does not exist",
                ex.getRequestURL());
        Problem problem = createProblemBuilder((HttpStatus) status, problemType, detail)
                .dateTime(OffsetDateTime.now())
                .userMessage(detail).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers,(HttpStatus) status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        ProblemType problemType = ProblemType.PARAMETER_INVALID;
        String detail = String.format("Property '%s' received value '%s', which is of an invalid type." +
                        " Correct and inform the value compatible with type '%s'.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        Problem problem = createProblemBuilder(status, problemType, detail)
                .dateTime(OffsetDateTime.now())
                .userMessage(detail).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, (HttpStatus) status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers,(HttpStatus) status, request);
        }

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = "The request body is invalid. Check syntax error.";

        Problem problem = createProblemBuilder( (HttpStatus) status, problemType, detail)
                .userMessage(END_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = String.format("Property '%s' does not exist."
                + "Correct or remove this property and try again.", path);

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(END_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(
            InvalidFormatException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = String.format("Property '%s' received value '%s', which is of an invalid type." +
                        " Correct and inform the value compatible with type '%s'.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(END_USER_GENERIC_ERROR_MESSAGE)
                .dateTime(OffsetDateTime.now())
                .userMessage(detail).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTITY_NOT_FOUND;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .dateTime(OffsetDateTime.now())
                .userMessage(detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .dateTime(OffsetDateTime.now())
                .userMessage(detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERROR_BUSINESS;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .dateTime(OffsetDateTime.now())
                .userMessage(detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if (body == null) {
            body = Problem.builder().title(ex.getMessage()).status(statusCode.value())
                    .dateTime(OffsetDateTime.now())
                    .userMessage(END_USER_GENERIC_ERROR_MESSAGE)
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder().title(ex.getMessage()).status(statusCode.value())
                    .dateTime(OffsetDateTime.now())
                    .userMessage(END_USER_GENERIC_ERROR_MESSAGE)
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder().status(status.value()).type(problemType.getUri()).title(problemType.getTitle()).detail(detail);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }
}
