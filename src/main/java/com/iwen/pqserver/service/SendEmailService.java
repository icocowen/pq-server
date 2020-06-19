package com.iwen.pqserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author i wen
 */
@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Value("${verify.code.subject}")
    private String subject;

    @Value("${verify.code.subject-for-forget}")
    private String subjectReal;

    @Value("${verify.code.text}")
    private String mailText;

    @Value("${verify.code.forget-password-text}")
    private String mailTextForForget;


    public void sendVerifyCode(String code, String toEmail) {
        encodeVerifyCodeText(code, toEmail,subject, mailText);
    }

    public void sendVerifyCodeForForget(String code, String toEmail) {
        encodeVerifyCodeText(code, toEmail,subjectReal ,mailTextForForget);
    }


    public void encodeVerifyCodeText(String code, String toEmail,String subjectReal, String mailTextReal) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subjectReal);
        message.setFrom(fromMail);
        message.setTo(toEmail);
        message.setSentDate(new Date());
        String mail = mailTextReal.replaceAll("\\{.*?verifyCode.*?\\}", code);
        mail = mail.replaceAll("\\{.*?toMail.*?\\}", toEmail);
        message.setText(mail);
        javaMailSender.send(message);
    }

}
