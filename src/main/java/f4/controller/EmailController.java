package f4.controller;

import f4.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email/v1")
public class EmailController {

  private final EmailService emailService;

  @PostMapping("/code")
  public ResponseEntity<String> sendAuthenticationCode(@RequestParam @Email String email) {
    emailService.sendAuthenticationCode(email);
    return new ResponseEntity<>("이메일 전송 성공", HttpStatus.OK);
  }
}
