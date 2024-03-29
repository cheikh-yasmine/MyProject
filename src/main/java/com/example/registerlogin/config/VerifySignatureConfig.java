package com.example.registerlogin.config;

import com.example.registerlogin.Esignature.VerifySignature;
import org.springframework.context.annotation.Bean;

public class VerifySignatureConfig {
    @Bean
    public VerifySignature verifySignature() {
        return new VerifySignature();
    }
}
