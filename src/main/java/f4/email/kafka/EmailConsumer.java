package f4.email.kafka;

import f4.email.dto.EmailEvent;
import f4.email.service.EmailService;
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

  @KafkaListener(
      topics = "${spring.kafka.topic.name}",
      groupId = "${spring.kafka.consumer.group-id}")
  public void consume(EmailEvent event) {
    LOGGER.info(
        String.format(
            "[%s] Email event received in email service and send email => %s",
            LocalDateTime.now(), event.getUserEmail()));
    emailService.sendSuccessfulBid(event);
  }
}
