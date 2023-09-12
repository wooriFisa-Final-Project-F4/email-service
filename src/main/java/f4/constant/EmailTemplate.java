package f4.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplate {
    SOURCE_EMAIL("kfromh0136@gmail.com"),
    AUTHENTICATION_CODE_EMAIL_SUBJECT("[ArteModerni] 이메일 인증 코드입니다."),
    AUTHENTICATION_CODE_EMAIL_TEMPLATE("AuthenticationCode.html"),
    SUCCESSFUL_BID_EMAIL_SUBJECT("[ArteModerni] 축하합니다. 입찰하신 상품이 낙찰 되었음을 알려드립니다."),
    SUCCESSFUL_BID_EMAIL_TEMPLATE("SuccessfulBid.html");
    private final String value;
}
