package f4.email.service;

import f4.email.dto.EmailEvent;

public interface EmailService {

  void sendAuthenticationCode(String email);

  void sendSuccessfulBid(EmailEvent emailEvent);

}
