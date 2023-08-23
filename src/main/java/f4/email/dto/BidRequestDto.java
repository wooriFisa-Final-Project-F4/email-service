package f4.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidRequestDto {
    private String userEmail;
    private String username;
    private String productName;
    private String productImage;
    private String Artist;
    private String bidPrice;
    private String auctionEndTime;
}
