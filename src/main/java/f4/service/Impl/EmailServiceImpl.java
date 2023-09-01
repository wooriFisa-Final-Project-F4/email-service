package f4.service.Impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import f4.constant.EmailTemplate;
import f4.constant.CustomErrorCode;
import f4.dto.EndedAuctionEvent;
import f4.exception.CustomException;
import f4.service.EmailService;
import f4.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
  private final AmazonSimpleEmailService amazonSimpleEmailService;
  private final ResourceLoader resourceLoader;

  // 인증 코드를 전송하는 메서드
  @Override
  public void sendAuthenticationCode(String email) {
    String authenticationCode = UUIDGenerator.generate();
    String htmlContent =
        loadAndReplaceTemplate(
            EmailTemplate.AUTHENTICATION_CODE_EMAIL_TEMPLATE.getValue(),
            "{{number}}",
            authenticationCode);
    sendEmail(email, EmailTemplate.AUTHENTICATION_CODE_EMAIL_SUBJECT.getValue(), htmlContent);
  }

  // 낙찰 알림 이메일을 전송하는 메서드
  @Override
  public void sendSuccessfulBid(EndedAuctionEvent event) {
    String htmlContent =
        loadAndReplaceTemplate(EmailTemplate.SUCCESSFUL_BID_EMAIL_TEMPLATE.getValue(), event);
    sendEmail(
        event.getUserEmail(), EmailTemplate.SUCCESSFUL_BID_EMAIL_SUBJECT.getValue(), htmlContent);
  }

  // 이메일을 전송하는 메서드
  private void sendEmail(String toEmail, String subject, String htmlContent) {
    SendEmailRequest request = createEmailRequest(toEmail, subject, htmlContent);
    try {
      amazonSimpleEmailService.sendEmail(request);
    } catch (AmazonServiceException e) {
      throw new CustomException(CustomErrorCode.EMAIL_SENDING_FAILED, e);
    }
  }

  // 이메일 요청 객체를 생성하는 메서드
  private SendEmailRequest createEmailRequest(String toEmail, String subject, String htmlContent) {
    return new SendEmailRequest()
        .withSource(EmailTemplate.SOURCE_EMAIL.getValue())
        .withDestination(new Destination().withToAddresses(toEmail))
        .withMessage(
            new Message()
                .withSubject(new Content().withData(subject))
                .withBody(new Body().withHtml(new Content().withData(htmlContent))));
  }

  // 템플릿을 불러오고, 해당 템플릿 내용을 BidRequestDto에 따라 바꿔주는 메서드
  private String loadAndReplaceTemplate(String templateFileName, EndedAuctionEvent emailEvent) {
    String htmlContent = loadHtmlTemplate(templateFileName);
    return htmlContent
        .replace("{{username}}", emailEvent.getUsername())
        .replace("{{productName}}", emailEvent.getProductName())
        .replace("{{productImage}}", emailEvent.getImage())
        .replace("{{artist}}", emailEvent.getArtist())
        .replace(
            "{{bidPrice}}", emailEvent.getAuctionPrice().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","))
        .replace("{{auctionEndTime}}", emailEvent.getAuctionEndTime().toString().split("T")[0]);
  }

  // 템플릿을 불러오고, 특정 플레이스홀더를 원하는 값으로 바꿔주는 메서드
  private String loadAndReplaceTemplate(
      String templateFileName, String placeholder, String replacement) {
    String htmlContent = loadHtmlTemplate(templateFileName);
    return htmlContent.replace(placeholder, replacement);
  }

  // 템플릿 파일을 불러오는 메서드
  private String loadHtmlTemplate(String templateFileName) {
    String templatePath = EmailTemplate.TEMPLATE_PATH.getValue() + templateFileName;
    try {
      return new String(
          Files.readAllBytes(Paths.get(resourceLoader.getResource(templatePath).getURI())));
    } catch (IOException e) {
      throw new CustomException(CustomErrorCode.EMAIL_TEMPLATE_LOADING_FAILED);
    }
  }
}
