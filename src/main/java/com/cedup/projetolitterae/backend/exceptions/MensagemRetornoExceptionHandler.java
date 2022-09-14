package com.cedup.projetolitterae.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MensagemRetornoExceptionHandler {

    @ExceptionHandler(MensagemRetornoException.class)
    public ResponseEntity handleApplicationException(MensagemRetornoException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMensagemRetorno());
    }

}