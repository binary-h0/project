package com.binaryho.global.exception;

import org.springframework.http.HttpStatus;

public class MessageConvertError extends CustomException {

    public MessageConvertError(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
