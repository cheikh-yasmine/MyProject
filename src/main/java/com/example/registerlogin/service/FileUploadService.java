package com.example.registerlogin.service;


import org.springframework.core.env.Environment;
import com.example.registerlogin.entity.FileUpload;
import com.example.registerlogin.exception.FileStorageException;
import com.example.registerlogin.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
@Service("FileUploadService")
public class FileUploadService  {
    private final FileUploadRepository fileUploadRepository;
    private  FileUpload fileUpload ;
    private Path uploadLocation ;
    @Autowired
    public FileUploadService(FileUploadRepository fileUploadRepository, Environment environment) {
        this.fileUploadRepository = fileUploadRepository;
        String uploadDir = environment.getProperty("file.upload.dir");
        if (uploadDir == null) {
            throw new IllegalArgumentException("File upload directory is not configured");
        }
        this.uploadLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create directory", ex);
        }}
//    @Autowired
//    public FileUploadService(FileUploadRepository fileUploadRepository,
//                             FileUpload fileUpload) {
//        this.fileUploadRepository = fileUploadRepository;
////        this.fileUpload = fileUpload;
//        this.uploadLocation= Paths.get(fileUpload.getUploadDir());
//        try {
//            Files.createDirectories(this.uploadLocation);
//        } catch(Exception ex){
//            throw new FileStorageException("Could not create directory" , ex );
//        }
//    }
    public FileUpload uploadFile(String ownedBy, String description, MultipartFile file) throws IOException {
        String originalFileName= StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path targetLocation = this.uploadLocation.resolve(originalFileName);
        Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
        FileUpload theFile = new FileUpload();
        theFile.setOwnedBy(ownedBy);
        theFile.setDescription(description);
        theFile.setType(file.getContentType());
        theFile.setName(originalFileName);
        theFile.setFile(file.getBytes());
        theFile.setUploadDir(String.valueOf(this.uploadLocation));
        return fileUploadRepository.save(theFile) ;


    }
}
