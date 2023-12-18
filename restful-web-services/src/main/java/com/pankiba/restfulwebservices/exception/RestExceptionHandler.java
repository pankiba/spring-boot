package com.pankiba.restfulwebservices.exception;

import static com.pankiba.restfulwebservices.exception.ApiError.stackTraceSummary;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Centralized exception handler for REST end points
 * 
 * @author pankiba
 *
 */
@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	/**
	 * 
	 * @param httpMessageNotReadableException
	 * @return
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleHttpMessageNotReadableException(
			HttpMessageNotReadableException httpMessageNotReadableException, WebRequest webRequest) {
		logger.error(" {} ", webRequest.getDescription(false));
		Throwable mostSpecificClause = httpMessageNotReadableException.getMostSpecificCause();
		if (mostSpecificClause != null) {
			return new ApiError(HttpStatus.BAD_REQUEST, "Request Body should be valid JSON object",
					mostSpecificClause.getClass().getName(), mostSpecificClause.getMessage());
		}

		return new ApiError(HttpStatus.BAD_REQUEST, "Problem parsing JSON",
				httpMessageNotReadableException.getMessage(), stackTraceSummary.apply(httpMessageNotReadableException));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleIllegalArgumentException(IllegalArgumentException illegalArgumentException,
			WebRequest webRequest) {
		logger.error(" {} ", webRequest.getDescription(false));
		return new ApiError(HttpStatus.BAD_REQUEST, "Bad argument", illegalArgumentException.getMessage(),
				illegalArgumentException.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ApiError handleHttpMediaTypeNotAcceptableException(
			HttpMediaTypeNotAcceptableException httpMediaTypeNotAcceptableException, WebRequest webRequest) {
		logger.error(" {} ", webRequest.getDescription(false));
		return new ApiError(HttpStatus.NOT_ACCEPTABLE, "Media Type Not Accepted",
				httpMediaTypeNotAcceptableException.getSupportedMediaTypes().toString(),
				httpMediaTypeNotAcceptableException.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ApiError handleHttpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException, WebRequest webRequest) {
		logger.error(" {} ", webRequest.getDescription(false));
		String supportedMediaTypes = "Supported content type: " + httpMediaTypeNotSupportedException.getContentType();
		String unSupportedMediaTypes = "Unsupported content type: "
				+ MediaType.toString(httpMediaTypeNotSupportedException.getSupportedMediaTypes());

		return new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Media Type Not Supported",
				String.join("", unSupportedMediaTypes + " || " + supportedMediaTypes),
				stackTraceSummary.apply(httpMediaTypeNotSupportedException));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ApiError handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException, WebRequest webRequest) {
		logger.error(" {} ", webRequest.getDescription(false));
		Object exceptionDetails = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.groupingBy(FieldError::getField,
						Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

		return new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "Validation Error", exceptionDetails);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public ApiError handleDataIntegrityViolation(DataIntegrityViolationException dataIntegrityViolationException,
			WebRequest webRequest) {
		logger.error(" {} ", webRequest.getDescription(false));
		return new ApiError(HttpStatus.CONFLICT, "Database Error",
				dataIntegrityViolationException.getLocalizedMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
			WebRequest webRequest) {
		logger.error(" {} ", webRequest.getDescription(false));
		return new ApiError(HttpStatus.NOT_FOUND, "Resource Not Found", resourceNotFoundException.getMessage());
	}

}
