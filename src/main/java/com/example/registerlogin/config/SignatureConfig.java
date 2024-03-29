package com.example.registerlogin.config;

import com.example.registerlogin.Esignature.CreateSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;

@Configuration
public class SignatureConfig {
    @Bean
    public CreateSignature createSignature() throws NoSuchAlgorithmException {
        return new CreateSignature();
    }
}
