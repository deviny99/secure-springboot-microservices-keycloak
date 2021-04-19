package com.demo.products.controllers;

import org.springframework.http.HttpStatus;

public class CustomException  extends RuntimeException {

    private final HttpStatus status;

    public CustomException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public static CustomException notFound(String message){
        return new CustomException(HttpStatus.NOT_FOUND,message);
    }

    public static CustomException forbidden(String message) {
        return new CustomException(HttpStatus.FORBIDDEN, message);
    }

    public static CustomException notAcceptable(String message) {
        return new CustomException(HttpStatus.NOT_ACCEPTABLE, message);
    }

    public static CustomException conflict(String message) {
        return new CustomException(HttpStatus.CONFLICT, message);
    }

    public static CustomException badRequest(String message) {
        return new CustomException(HttpStatus.BAD_REQUEST, message);
    }

    public static CustomException preconditionFailed(String message) {
        return new CustomException(HttpStatus.PRECONDITION_FAILED, message);
    }

    public static CustomException unsupportedMediaType(String message) {
        return new CustomException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message);
    }
}