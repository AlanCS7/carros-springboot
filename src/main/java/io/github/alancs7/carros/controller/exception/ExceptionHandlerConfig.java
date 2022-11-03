package io.github.alancs7.carros.controller.exception;

import io.github.alancs7.carros.service.exception.ObjectNotFoundException;
import lombok.Getter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;
import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler({
            EmptyResultDataAccessException.class,
            ObjectNotFoundException.class
    })
    public ResponseEntity errorNotFound(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Resource Not Found";
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity errorBadRequest(Exception e) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class
    })
    protected ResponseEntity errorMethodNotAllowed(Exception ex) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        String error = "Method Not Allowed";
        StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({
            AccessDeniedException.class
    })
    protected ResponseEntity errorAccessDeniedException(Exception ex) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        String error = "Access Denied Exception";
        StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage());
        return ResponseEntity.status(status).body(err);
    }
}

@Getter
class StandardError implements Serializable {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;

    public StandardError(Instant timestamp, Integer status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
