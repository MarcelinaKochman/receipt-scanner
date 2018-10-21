package com.wfiis.receiptscanner.ocr.alghorithms;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public class Grayscale implements ImageAlgorithm {
    @Override
    public BufferedImage process(BufferedImage image) {
        BufferedImage grayScaleImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);

        Graphics g = grayScaleImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return grayScaleImage;
    }
}
