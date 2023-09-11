package f4.controller;

import f4.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class EmailController {

  private final EmailService emailService;

  @PostMapping("/code")
  public ResponseEntity<String> sendAuthenticationCode(@RequestParam @Email String email) {
    log.info("이메일 인증 서비스 email : {}", email);
    emailService.sendAuthenticationCode(email);
    return new ResponseEntity<>("이메일 전송 성공", HttpStatus.OK);
  }
}
