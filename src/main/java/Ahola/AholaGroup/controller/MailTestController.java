package Ahola.AholaGroup.controller;


import Ahola.AholaGroup.email.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailTestController {
    private final EmailServiceImpl emailService;


    @SneakyThrows
    @GetMapping("/mail")
    public ResponseEntity<Object> testMail(){
      emailService.sendmail();
        return ResponseEntity.ok("Mail sent");
    }
}
