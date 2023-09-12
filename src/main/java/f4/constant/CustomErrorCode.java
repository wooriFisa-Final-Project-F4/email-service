package f4.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
  EMAIL_TEMPLATE_LOADING_FAILED(401, "이메일 템플릿을 불러오는데 실패했습니다."),
  EMAIL_SENDING_FAILED(402, "이메일 전송에 실패했습니다.");

  private final int code;
  private final String message;
}
