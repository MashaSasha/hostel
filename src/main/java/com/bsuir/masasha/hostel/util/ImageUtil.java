package com.bsuir.masasha.hostel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public final class ImageUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);
    private static final String SLASH = "/";

    private ImageUtil() {
    }

    public static String upload(MultipartFile image, String targetPath) {
        String resultFilename = "";
        if (image == null) {
            return resultFilename;

        }

        String absoluteUploadPath = Optional.ofNullable(ImageUtil.class.getClassLoader().getResource(targetPath))
                .map(url -> new File(url.getPath()).getAbsolutePath())
                .orElse("");

        String uuidFile = UUID.randomUUID().toString();
        resultFilename = uuidFile + "." + image.getOriginalFilename();
        try {
            image.transferTo(new File(absoluteUploadPath + SLASH + resultFilename));
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
