package com.progettoweb.civediamodomanibe.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailService {

    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        new Thread(() -> {
            try {
                MimeMessage message = emailSender.createMimeMessage();
                message.setText(text);
                message.setSubject(subject);
                message.setFrom("devtestiet@outlook.it");
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

                emailSender.send(message);
            } catch (MailException | MessagingException exception) {
                log.error("Send email failed: " + exception.getMessage());
            }
        }).start();
    }
}
