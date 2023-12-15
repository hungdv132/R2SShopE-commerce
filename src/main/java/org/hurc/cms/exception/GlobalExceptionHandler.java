package org.hurc.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CmsException.class)
  public ResponseEntity<ErrorDetails> handleCmsException(CmsException exception,
                                                         WebRequest webRequest) {

    ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        exception.getMessage(),
        webRequest.getDescription(false)
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleCmsException(ResourceNotFoundException exception,
                                                         WebRequest webRequest) {

    ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        exception.getMessage(),
        webRequest.getDescription(false)
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
}