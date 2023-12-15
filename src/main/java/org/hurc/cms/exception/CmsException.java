package org.hurc.cms.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CmsException extends RuntimeException{
  private final HttpStatus status;
  private final String message;
}
