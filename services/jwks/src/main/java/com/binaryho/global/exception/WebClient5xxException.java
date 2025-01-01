package com.binaryho.global.exception;

import org.springframework.http.HttpStatus;

public class WebClient5xxException extends CustomException {

    public WebClient5xxException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
