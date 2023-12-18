package com.pankiba.restfulwebservices.exception;

import static com.pankiba.restfulwebservices.exception.ApiError.stackTraceSummary;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.LOWEST_PRECEDENCE)
public class GenericExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(Exception exception) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                exception.getLocalizedMessage(), stackTraceSummary.apply(exception));
    }

}
