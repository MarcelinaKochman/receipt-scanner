package com.wfiis.receiptscanner.ocr.alghorithms;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public class Resize implements ImageAlgorithm {

    private final int newH = 2000;

    @Override
    public BufferedImage process(BufferedImage image) {

//        int newH = (int) (image.getHeight() * 0.5);
//        int newW = (int) (image.getWidth() * 0.5);
        int newW = (int) (((double) image.getWidth() / (double) image.getHeight()) * (double) newH);

        Image tmp = image.getScaledInstance(newW, newH, image.getType());
        BufferedImage dimg = new BufferedImage(newW, newH, image.getType());

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
