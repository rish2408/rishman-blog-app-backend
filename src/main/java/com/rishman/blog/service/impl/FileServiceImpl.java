package com.rishman.blog.service.impl;

import com.rishman.blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // Get file name
        String fileName = file.getOriginalFilename();

        // Generate random name
        String randomID = UUID.randomUUID().toString();
        String newFileName = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

        // Create complete file path
        String filePath = path + File.separator + newFileName;

        // Create directories if they don't exist
        Path uploadPath = Paths.get(path);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Copy file to destination
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return newFileName;
    }

    @Override
    public InputStream getImage(String path, String fileName) throws FileNotFoundException {

        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
}
