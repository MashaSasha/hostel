package com.bsuir.masasha.hostel;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class ImageUtil {

    private ImageUtil() {}

    public static String getNewPath(MultipartFile image, String uploadPath) {
        String resultFilename = "";
        if (image != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + image.getOriginalFilename();

            try {
                image.transferTo(new File(uploadPath + "/" + resultFilename));
            } catch (IOException e) {
                resultFilename = "";
            }

            return resultFilename;
        }
        return resultFilename;
    }
}
