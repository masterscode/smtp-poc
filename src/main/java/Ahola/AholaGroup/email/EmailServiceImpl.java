package Ahola.AholaGroup.email;

import Ahola.AholaGroup.dto.EmailDto;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface EmailServiceImpl {
    void sendEmailAlert(EmailDto emailDto);
    public void sendHtmlMail(String to, String subject, String content);

    void sendmail() throws AddressException, MessagingException, IOException;
}
