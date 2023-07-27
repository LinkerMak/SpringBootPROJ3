package com.example.springbootproj.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
/*
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
*/

import java.nio.charset.StandardCharsets;

@Service
public class DefaultEmailService implements EmailService{

    @Autowired
    public JavaMailSender emailSender;

    /*@Autowired
    private SpringTemplateEngine templateEngine;
    @Override
    public void sendSimpleEmailWithAttachment(EmailContext email) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getContext());
        String emailContent = templateEngine.process(email.getTemplateLocation(), context);

        mimeMessageHelper.setTo(email.getEmail());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setText(emailContent, true);
        emailSender.send(message);
    }*/

    @Override
    public void sendSimpleEmailWithAttachment(EmailContext email) throws MessagingException {
        return;
    }

    @Override
    public void sendSimpleEmail(String toAddress,String fromAddress, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAddress);
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }

}
