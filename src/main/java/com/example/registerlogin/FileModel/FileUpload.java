package com.example.registerlogin.FileModel;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Entity
@Table
@Component
public class FileUpload implements Serializable {
    @Id
    @Column(name = "employee_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;
    @Column(name = "file_name", length = 255)
    private String name;
    @Column(name = "file_type", length = 255)
    private String type;
   @Column(name = "file_owner", length = 255)
    private String ownedBy;
    @Column(name = "file_description", length = 255)
    private String description;

    @Lob
    private byte[] file;
    @Column(name = "upload_dir")
    private String uploadDir;
}
