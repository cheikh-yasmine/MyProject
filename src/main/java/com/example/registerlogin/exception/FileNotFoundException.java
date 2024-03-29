package com.example.registerlogin.exception;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException(Integer fileId) {
        super("File with ID " + fileId + " not found");
    }
}
