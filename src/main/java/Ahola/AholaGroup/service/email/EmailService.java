package Ahola.AholaGroup.service.email;

import Ahola.AholaGroup.dto.EmailDto;

import Ahola.AholaGroup.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements EmailServiceImpl {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;


    public void sendHtmlMail(String to, String subject, String content){
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        try{
//            MimeMessageHelper helper = setInfo(to, subject, content, message);
//            javaMailSender.send(message);
//        }catch (MessagingException e){
//            e.printStackTrace();
//        }
    }
//
//    private MimeMessageHelper setInfo(String to, String subject, String content, MimeMessage message) throws MessagingException {
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(senderEmail);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        return  helper;
//    }




    @Override
    public void sendEmailAlert(EmailDto emailDto) {
        if (javaMailSender == null) {
            throw new ApiException("JAVA SENDER NOT AVAILABLE");
        }

        log.info("--> DTO options before {}", emailDto.getRecipient());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(senderEmail);
        mailMessage.setTo(emailDto.getRecipient());
        mailMessage.setText(emailDto.getMessageBody());
        mailMessage.setSubject(emailDto.getSubject());

        log.info("--> DTO options before {}", javaMailSender);

        try {
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }


    @Override
    public void sendmail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mageexian@gmail.com", "$Peace2lover");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("mageexian@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ogbinakaemmanuel@gmail.com"));
        msg.setSubject("Tutorials point email");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("/var/tmp/image19.png");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }



}
