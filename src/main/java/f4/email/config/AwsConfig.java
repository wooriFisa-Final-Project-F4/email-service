package f4.email.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
  @Value("${aws.ses.access-key}")
  private String accessKey;

  @Value("${aws.ses.secret-key}")
  private String secretKey;

  @Value("${aws.region}")
  private String region;

  @Bean
  public AmazonSimpleEmailService amazonSimpleEmailService() {
    return AmazonSimpleEmailServiceClient.builder()
        .withRegion(region)
        .withCredentials(
            new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
        .build();
  }
}
