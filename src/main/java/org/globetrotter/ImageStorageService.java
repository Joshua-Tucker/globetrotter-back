package org.globetrotter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageService {

    @Value("${image.upload.dir}")
    private String imageUploadDir;

    public String storeImage(MultipartFile file) throws IOException {
        String imageName = UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];
        byte[] bytes = file.getBytes();
        Path path = Paths.get(imageUploadDir + imageName);
        Files.write(path, bytes);
        return imageName;
    }

    public File getImage(String imageName) {
        String filePath = imageUploadDir + imageName;
        return new File(filePath);
    }
}
