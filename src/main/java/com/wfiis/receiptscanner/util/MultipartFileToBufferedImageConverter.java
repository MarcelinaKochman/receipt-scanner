package com.wfiis.receiptscanner.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class MultipartFileToBufferedImageConverter {

    public static BufferedImage convert(MultipartFile multipartFile) {
        BufferedImage image;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            image = toImageWithHonorationOfEXIFOrientationFlag(inputStream);
        } catch (IOException e) {
            image = null;
        }
        return image;
    }

    private static BufferedImage toImageWithHonorationOfEXIFOrientationFlag(InputStream inputStream) throws IOException {
        return Thumbnails.of(inputStream).scale(1).asBufferedImage();
    }
}
