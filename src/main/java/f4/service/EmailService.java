package f4.service;

import f4.dto.EndedAuctionEvent;

public interface EmailService {

  void sendAuthenticationCode(String email);

  void sendSuccessfulBid(EndedAuctionEvent emailEvent);
}
