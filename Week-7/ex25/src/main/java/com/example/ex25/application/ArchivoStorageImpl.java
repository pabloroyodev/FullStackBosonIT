package com.example.ex25.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ArchivoStorageImpl implements ArchivoStorage {

    @Value("${directorio:}")
    String directorio;
    private Path root;

    @Override
    public void createFolder() throws Exception {
            try {
                Files.createDirectory(root);
            } catch (Exception e) {
                throw new Exception("Error while creating the folder");
            }
    }

    @Override
    public void save(MultipartFile file) throws Exception {
        try{
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new Exception("Error while saving the file");
        }
    }

    @Override
    public Resource load(String filename) throws Exception {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception("File not found");
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e);
        }
    }

    @Override
    public void deleteAll() {
        if (directorio == null || directorio.equals("")) {
            System.out.println("Folder name not specified, storage folder set to be Uploads");
            this.root = Paths.get("uploads");
        } else {
            this.root = Paths.get(directorio);
        }
        FileSystemUtils.deleteRecursively(root.toFile());
    }
}
