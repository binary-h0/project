package com.binaryho.global.exception;

import org.springframework.http.HttpStatus;

public class WebClient4xxException extends CustomException {

    public WebClient4xxException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
