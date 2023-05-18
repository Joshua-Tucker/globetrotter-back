package org.globetrotter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageStorageService {

    @Value("${image.upload.dir}")
    private String imageUploadDir;

    public List<String> storeImages(MultipartFile[] files) throws IOException {
        List<String> imageNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String imageName = UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];
            byte[] bytes = file.getBytes();
            Path path = Paths.get(imageUploadDir + imageName);
            Files.write(path, bytes);
            imageNames.add(imageName);
        }
        return imageNames;
    }

    public File getImage(String imageName) {
        String filePath = imageUploadDir + imageName;
        return new File(filePath);
    }
}
