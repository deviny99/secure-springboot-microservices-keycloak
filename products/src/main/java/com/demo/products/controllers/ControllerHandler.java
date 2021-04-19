package com.demo.products.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> metodo (CustomException customException){
        Map<String,Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status",customException.getStatus().value());
        map.put("error",customException.getStatus().getReasonPhrase());
        map.put("message",customException.getLocalizedMessage());
        map.put("path","");
        return ResponseEntity.status(customException.getStatus())
                .body(map);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body(Map.of(
                "mensagem", "Não foi possível ler a requisição.\n"+exception.getMessage()));
    }


}
