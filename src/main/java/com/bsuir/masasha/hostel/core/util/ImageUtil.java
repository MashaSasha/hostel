package com.bsuir.masasha.hostel.core.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public final class ImageUtil {

    private ImageUtil() {
    }

    public static String getNewPath(MultipartFile image, String uploadPath) {
        String resultFilename = "";
        if (image != null) {

            String absoluteUploadPath = Optional.ofNullable(ImageUtil.class.getClassLoader().getResource(uploadPath))
                    .map(url -> new File(url.getPath()).getAbsolutePath())
                    .orElse("");

            String uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + image.getOriginalFilename();

            try {
                image.transferTo(new File(absoluteUploadPath + "/" + resultFilename));
            } catch (IOException e) {
                resultFilename = "";
            }

            return resultFilename;
        }
        return resultFilename;
    }
}
