package com.binaryho.global.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    private Status status;
    private String message;
    private T data;

    @Builder
    public Result(Status status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .status(Status.SUCCESS)
                .data(data)
                .build();
    }

    public static <T> Result<T> fail(String message) {
        return Result.<T>builder()
                .status(Status.FAIL)
                .message(message)
                .build();
    }
}
