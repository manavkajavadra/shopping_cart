package com.example.shopping_cart.advice;

import com.example.shopping_cart.comman_response_dto.CommonResponse;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class MyGlobalExceptionHandler extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    @ExceptionHandler(MyGlobalExceptionHandler.class)
    public ResponseEntity<CommonResponse> handleMyGlobalExceptionHandler(MyGlobalExceptionHandler ex) {
        return new ResponseEntity<>(new CommonResponse(false, ex.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {

        logger.error("HttpMessageNotReadableException : ", ex);

        String message = "Invalid request body";

        Throwable cause = ex.getCause();
        if (cause instanceof UnrecognizedPropertyException unrecognizedEx) {
            String fieldName = unrecognizedEx.getPropertyName();
            message = "Invalid field: '" + fieldName + "'";
        }

        CommonResponse commonResponse = new CommonResponse(false, message, null);

        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<CommonResponse> handleMyCustomException(MyCustomException mce) {
        return new ResponseEntity<>(new CommonResponse(false, mce.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> haandleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        CommonResponse commonResponse = new CommonResponse(false, "Validation failed", errors);

        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);

    }
}