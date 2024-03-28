package com.example.registerlogin.controller;


import org.springframework.web.bind.annotation.*;

import com.example.registerlogin.entity.FileUpload;
import com.example.registerlogin.service.FileUploadService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/files")
public class FileUploadController {


    private final FileUploadService fileUploadService;



    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;

    }
    @PostMapping
    public ResponseEntity<FileUpload> uploadFile(
            @RequestParam("ownedBy") String ownedBy,
           @RequestParam("description") String description,
        @RequestParam("file") MultipartFile file) throws IOException {
        FileUpload theFile = fileUploadService.uploadFile(ownedBy, description, file);
        return new ResponseEntity<>(theFile, HttpStatus.OK);
    }

}
