package f4.exception;

import f4.constant.CustomErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
  private final CustomErrorCode customErrorCode;

  public CustomException(CustomErrorCode customErrorCode) {
    super(customErrorCode.getMessage());
    this.customErrorCode = customErrorCode;
  }
  public CustomException(CustomErrorCode customErrorCode, Throwable cause) {
    super(customErrorCode.getMessage(), cause);
    this.customErrorCode = customErrorCode;
  }
}
