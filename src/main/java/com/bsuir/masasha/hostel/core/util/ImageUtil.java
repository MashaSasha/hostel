package com.bsuir.masasha.hostel.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public final class ImageUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);
    private static final String DEFAULT_NAME = "";
    private static final String SLASH_SEPARATOR = "/";

    private ImageUtil() {
    }

    public static String upload(MultipartFile imageFile, String uploadPath) {
        if (imageFile == null) {
            return DEFAULT_NAME;
        }
        if (imageFile.getOriginalFilename().isEmpty()) {
            return DEFAULT_NAME;
        }
        URL urlResource = ImageUtil.class.getClassLoader().getResource(uploadPath);
        String absoluteUploadPath = Optional.ofNullable(urlResource)
                .map(url -> new File(url.getPath()).getAbsolutePath())
                .orElse(DEFAULT_NAME);

        String id = UUID.randomUUID().toString();
        String resultFilename = id + "." + imageFile.getOriginalFilename();
        try {
            String fullPath = absoluteUploadPath + SLASH_SEPARATOR + resultFilename;
            imageFile.transferTo(new File(fullPath));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return resultFilename;
    }

    public static boolean delete(String imagePath) {
        Path path = Paths.get(imagePath);
        if (!path.toFile().exists()) {
            return false;
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }
}
