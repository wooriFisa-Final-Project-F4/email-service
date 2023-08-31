package f4.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class EndedAuctionEvent {
  private Long userId;
  private String userEmail;
  private String username;
  private String productName;
  private String productImage;
  private String artist;
  private String auctionPrice;
  private LocalDateTime auctionEndTime;
}
