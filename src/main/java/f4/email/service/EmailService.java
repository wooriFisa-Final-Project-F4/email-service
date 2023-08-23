package f4.email.service;

import f4.email.dto.BidRequestDto;

public interface EmailService {

  void sendAuthenticationCode(String email);

  void sendSuccessfulBid(BidRequestDto bidRequestDto);

}
