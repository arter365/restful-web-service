package com.factorysalad.restfulwebservice.e_exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

// @ControllerAdvice를 사용하면 Controller에서 발생하는 Exception을 한군데서 처리 할 수 있다.
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    // Exception 에러가 발생할 때 처리하는 헨들러
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // UserNotFoundException 에러가 발생 할 때 처리하는 헨들러
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // 유효성 검사에러를 처리한다.
    // ResponseEntityExceptionHandler의 메서드를 오버라이드해서 처리한다. 오버라이드 시 @Override 어노테이션을 붙이면 메서드명이 다를 때 오류를 알려준다.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
