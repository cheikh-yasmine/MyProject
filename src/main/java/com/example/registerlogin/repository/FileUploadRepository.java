package com.example.registerlogin.repository;

import com.example.registerlogin.FileModel.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload,Long > {
}
