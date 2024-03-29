package com.example.registerlogin.Esignature;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import java.security.*;
import java.util.Scanner;
import java.io.UnsupportedEncodingException;

@Component
public class CreateSignature {
    private KeyPairGenerator keyPairGenerator;
    private KeyPair keyPair;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private Signature signature;

    public CreateSignature() throws NoSuchAlgorithmException {
        this.keyPairGenerator=KeyPairGenerator.getInstance("RSA");//initialize keypair generator with RSA algorithm
        this.keyPairGenerator.initialize(2048);
        this.keyPair=keyPairGenerator.generateKeyPair();
        this.signature=Signature.getInstance("SHA256withRSA");// create signature using algorithm SHA256withRSA
    }

    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public byte[] generateSignature(String message) throws InvalidKeyException, SignatureException, UnsupportedEncodingException {
        signature.initSign(getPrivateKey());
        byte[] bytes = message.getBytes("UTF-8");
        signature.update(bytes);
        byte[] finalSignature=signature.sign();
        return finalSignature;
    }

    public String convertSignature(byte[] signature){//convert signature
        return new String(Base64.encode(signature));
    }

    public String convertPublicKey(PublicKey publicKey){//convert publicKey
        return new String(Base64.encode(publicKey.getEncoded()));
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, SignatureException, InvalidKeyException {
        //CREATE PUBLIC AND PRIVATE KEY
        CreateSignature createSignature = new CreateSignature();//create object

        System.out.println(createSignature.getPrivateKey());

        System.out.println(createSignature.getPublicKey());

        //CREATE SIGNATURE
        Scanner input = new Scanner(System.in);

        String message = input.nextLine();
        byte[] signature = createSignature.generateSignature(message);
        System.out.println(signature);

        //CONVERT SIGNATURE
        String signatureString = createSignature.convertSignature(signature);

        System.out.println(signatureString);

        //CONVERT PUBLIC KEY
        String publicKeyString = createSignature.convertPublicKey(createSignature.getPublicKey());
        System.out.println(publicKeyString);

    }
}
