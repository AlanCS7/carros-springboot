package io.github.alancs7.carros.api.infra.exception;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;

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