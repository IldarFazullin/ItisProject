package com.example.ItisProject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorCode {
    private String message;
    private int code;

    public static ErrorCode INVALID_EMAIL_OR_PASSWORD = new ErrorCode("Wrong user or password", 1);
    public static ErrorCode USER_NOT_FOUND = new ErrorCode("User is not found", 2);
    public static ErrorCode USER_EXISTS_ALREADY = new ErrorCode("User with this email already exists", 3);
}
