package com.example.springbootproj.service.email;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendSimpleEmailWithAttachment(EmailContext email) throws MessagingException;

    public void sendSimpleEmail(String toAddress, String subject, String message);
}
