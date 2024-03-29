package com.example.registerlogin.Esignature;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class SignatureController {



    private final CreateSignature createSignature;
    private final VerifySignature verifySignature;

    public SignatureController(CreateSignature createSignature, VerifySignature verifySignature) {
        this.createSignature = createSignature;
        this.verifySignature = verifySignature;
    }

//    @PostMapping("/generateSignature")
//    public String generateSignature(@RequestBody String message) throws Exception {
//        byte[] signature = createSignature.generateSignature(message);
//        return createSignature.convertSignature(signature);
//    }
@PostMapping("/generateSignature")
public ResponseEntity<Object> generateSignature(@RequestBody String message) throws Exception {
    byte[] signature = createSignature.generateSignature(message);
    String signatureString = createSignature.convertSignature(signature);
    String publicKeyString = createSignature.convertPublicKey(createSignature.getPublicKey());

    Map<String, String> response = new HashMap<>();
    response.put("signature", signatureString);
    response.put("publicKey", publicKeyString);

    return ResponseEntity.ok(response);
}

    @PostMapping("/verifySignature")
    public ResponseEntity<Boolean> verifySignature(@RequestBody SignatureVerificationRequest request) throws Exception {
        byte[] signatureByte = Base64.decode(request.getSignature().getBytes());
        boolean result = verifySignature.verifySignature(request.getMessage(), signatureByte, verifySignature.deConvertPublicKey(request.getPublicKey()));
        return ResponseEntity.ok(result);
    }
    }

