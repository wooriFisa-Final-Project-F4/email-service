package f4.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmailEvent {
  private String userEmail;
  private String username;
  private String productName;
  private String productImage;
  private String artist;
  private String auctionPrice;
  private String auctionEndTime;
}
