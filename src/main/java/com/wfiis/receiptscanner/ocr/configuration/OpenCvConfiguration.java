package com.wfiis.receiptscanner.ocr.configuration;

import nu.pattern.OpenCV;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class OpenCvConfiguration {

    @PostConstruct
    public void loadOpenCv() {
        OpenCV.loadLocally();
    }

}
