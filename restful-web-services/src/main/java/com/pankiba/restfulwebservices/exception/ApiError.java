package com.pankiba.restfulwebservices.exception;

import static java.util.stream.Collectors.joining;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Function;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * REST API error response pay load
 * 
 * @author pankiba
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiError {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;

	private HttpStatus httpStatus;

	private String errorMessage;

	private Object exceptionDetails;

	private String developerMessage;

	private String moreInfo;

	public static final Function<Exception, String> stackTraceSummary = exception -> Arrays
			.stream(exception.getStackTrace()).limit(5).map(StackTraceElement::toString).collect(joining("\n"));

	private ApiError() {
		timeStamp = LocalDateTime.now();
	}

	public ApiError(HttpStatus httpStatus, String errorMessage, Object exceptionDetails) {
		this();
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.exceptionDetails = exceptionDetails;
	}

	public ApiError(HttpStatus httpStatus, String errorMessage, Object exceptionDetails,
			String developerMessage) {
		this();
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.exceptionDetails = exceptionDetails;
		this.developerMessage = developerMessage;
	}

}
