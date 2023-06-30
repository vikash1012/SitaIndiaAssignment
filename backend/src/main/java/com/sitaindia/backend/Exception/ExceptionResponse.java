package com.sitaindia.backend.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    String ErrorCode;
    String message;
}
