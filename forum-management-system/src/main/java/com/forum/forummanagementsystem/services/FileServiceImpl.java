package com.forum.forummanagementsystem.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        // Път към директорията за качване
        Path path = Paths.get(uploadDir);

        // Създаване на директория, ако не съществува
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        String filename = file.getOriginalFilename();
        Path targetLocation = path.resolve(filename);

        // Копиране на файла в директорията
        Files.copy(file.getInputStream(), targetLocation);

        return filename;  // Връща името на качения файл
    }

    public Path load(String filename) {
        // Връща пътя за зареждане на даден файл
        return Paths.get(uploadDir).resolve(filename);
    }
}
