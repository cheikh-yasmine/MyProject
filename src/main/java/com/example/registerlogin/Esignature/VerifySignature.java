package com.example.registerlogin.Esignature;


import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.*;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
@Component
public class VerifySignature {



        public static PublicKey deConvertPublicKey(String publicKeyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
            byte[] publicKeyByte = Base64.decode(publicKeyString.getBytes());
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyByte));
            return publicKey;
        }

        public static byte[] deConvertSignature(String signatureString) throws NoSuchAlgorithmException, InvalidKeySpecException {
            byte[] signature = Base64.decode(signatureString.getBytes());
            return signature;
        }

        public static boolean verifySignature(String message, byte[] signature, PublicKey publicKey) throws InvalidKeyException, SignatureException, UnsupportedEncodingException, NoSuchAlgorithmException {
            byte[] messageByte = message.getBytes();
            Signature signature1 = Signature.getInstance("SHA256withRSA");
            signature1.initVerify(publicKey);
            signature1.update(messageByte);
            return signature1.verify(signature);
        }

        public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, SignatureException, InvalidKeyException {
            String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmw9qVNFXpesfNpKAP2eUoVBHq0dzYUhp4qjTNGzDxFRJM4SglzLtkvmH7O52C6H8oG/yXhsVgTMJuRzWSvXJcLE0yPd/PC1GgHwVR2U6yYAealnfH4k9QjscnW2rYaDuVNOE+C4CTBVO8w9j+BZvlA+DbkEUj84vVDkO0kKGku405t1JenCnzWWxLgrtrwy+GeX1Etfxj4g5OWAzSR99TvBz96i6UQ0AqhCL0u0dCuPXOlqgq+NSREmj4Ya+UjqUoIyWl8kG5XbXOQPZTOlKybwaLu/Zcm5pVTkBvuNS48O47oH6DeaN5iZX9lVw6xp9y4NKAnjgCx3wTxLX4DiceQIDAQAB";
            String signature = "cwHK5Sk4ci/y08yNWKkfWHL6M/uHKximJuGpnHvgs776BLFshxiGPBqVMVQ8eL3XvCSlWdZBzj0sgYqLDybvtRvcajzcM4DK2iI4ExfYYC4hcjLsiI/e1pnViIj/DS3c5IMhuzaFIqhfgzwWkYSJTlosEdQsZpEmPgH5ul40q1XTLa7YzU+ZuWlc9MqgfKgMoWh/t/JkPfhjkJ7DLSX99wFu03hIOjV2BGyqIWm/e+Ag6qOjGLANJg0qbG5dRuy/BKA5pu0naePYCizyLXjKoZcdPC+yyMFyaeqQj6VAkmYlRUitOa+44maOty/nCisowaKs026Xn0CJUzmLcuwykw==";
            String message = "Yasmine Cheikh";

            PublicKey publicKeyDeConvert = VerifySignature.deConvertPublicKey(publicKey);
            System.out.println(publicKeyDeConvert);


            byte[] signatureDeConvert = VerifySignature.deConvertSignature(signature);
            System.out.println(signatureDeConvert);


            boolean result = VerifySignature.verifySignature(message, signatureDeConvert, publicKeyDeConvert);
            System.out.println(result);

        }

    }
