package com.example.registerlogin.MailSend;

import org.springframework.web.multipart.MultipartFile;

public interface MailService {
    interface EmailService {
        String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body);
    }
}