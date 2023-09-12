package f4.kafka;

import f4.dto.EndedAuctionEvent;
import f4.service.EmailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmailConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);
  private EmailService emailService;

  @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
  public void consume(EndedAuctionEvent event) {
    emailService.sendSuccessfulBid(event);
    LOGGER.info(
        String.format(
            "[%s] [%s] event received in email service and send email to => %s",
            LocalDateTime.now(), event, event.getUserEmail()));
  }
}
