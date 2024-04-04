package com.example.registerlogin.MailSend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class ConfigurationMail {



        @Bean
        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername("y.c.yass@gmail.com");
            mailSender.setPassword("xwkkicjpdnadayww");
            mailSender.setJavaMailProperties(getMailProperties());

            return mailSender;
        }

        private Properties getMailProperties() {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", true);
            properties.put("mail.smtp.starttls.enable", true);
            return properties;
        }
    }


