package com.binaryho.global.exception;

import org.springframework.http.HttpStatus;

public class NoResponseException extends CustomException {

    public NoResponseException(String message) {
        super(HttpStatus.NO_CONTENT, message);
    }

}
