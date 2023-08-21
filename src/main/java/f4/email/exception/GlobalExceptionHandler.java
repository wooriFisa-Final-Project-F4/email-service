package f4.email.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({CustomException.class})
  public ResponseEntity<ErrorDetails> customExceptionHandler(CustomException e) {
    return new ResponseEntity<>(
        ErrorDetails.builder()
            .timestamp(LocalDateTime.now())
            .code(e.getCustomErrorCode().getCode())
            .message(e.getCustomErrorCode().getMessage())
            .build(),
        HttpStatus.BAD_REQUEST);
  }
}
