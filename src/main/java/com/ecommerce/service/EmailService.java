package com.ecommerce.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${email.from}")
    private String fromAddress;


    private SimpleMailMessage makeMailMessage() throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAddress);
        return simpleMailMessage;
    }

    public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text) throws MessagingException {
        SimpleMailMessage message = makeMailMessage();

        message.setTo(userEmail);
        message.setSubject(subject);
        message.setText(text);

        try {
            javaMailSender.send(message);
        }catch (MailException e){
            throw new MailSendException("Failed to send Email");
        }

    }

//    public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text){
//        try{
//            MimeMessage mimeMsg = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMsg, "utf-8");
//            mimeMessageHelper.setSubject(subject);
//            mimeMessageHelper.setText(text);
//            mimeMessageHelper.setTo(userEmail);
//            javaMailSender.send(mimeMsg);
//
//        }
//        catch (MailException | MessagingException e){
//            throw new MailSendException("Failed to send Email");
//        }
//    }



}
