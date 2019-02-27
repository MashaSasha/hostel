package com.bsuir.masasha.hostel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public final class ImageUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);

    private ImageUtil() {
    }

    public static String upload(MultipartFile image, String uploadPath) {
        String resultFilename = "";
        if (image == null) {
            return resultFilename;
        }

        String absoluteUploadPath = Optional.ofNullable(ImageUtil.class.getClassLoader().getResource(uploadPath))
                .map(url -> new File(url.getPath()).getAbsolutePath())
                .orElse("");

        String uuidFile = UUID.randomUUID().toString();
        resultFilename = uuidFile + "." + image.getOriginalFilename();
        try {
            image.transferTo(new File(absoluteUploadPath + "/" + resultFilename));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return resultFilename;
    }

    public static void delete(String uploadedImage) {

    }
}
