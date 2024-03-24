package com.example.registerlogin.exception;

public class FileStorageException extends RuntimeException{
    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
