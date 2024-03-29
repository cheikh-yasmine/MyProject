package com.example.registerlogin.Esignature;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignatureVerificationRequest {
    private String message;
    private String signature;
    private String publicKey;
}
