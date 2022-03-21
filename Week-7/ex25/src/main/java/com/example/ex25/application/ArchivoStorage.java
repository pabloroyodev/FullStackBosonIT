package com.example.ex25.application;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoStorage {
    void createFolder() throws Exception;
    void save(MultipartFile file) throws Exception;
    Resource load(String filename) throws Exception;
    void deleteAll();
}
