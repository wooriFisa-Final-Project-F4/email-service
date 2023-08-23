package f4.email.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
  private LocalDateTime timestamp;
  private int code;
  private String message;
}
