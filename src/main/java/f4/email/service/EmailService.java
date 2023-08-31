package f4.email.service;

import f4.email.dto.EndedAuctionEvent;

public interface EmailService {

  void sendAuthenticationCode(String email);

  void sendSuccessfulBid(EndedAuctionEvent emailEvent);
}
