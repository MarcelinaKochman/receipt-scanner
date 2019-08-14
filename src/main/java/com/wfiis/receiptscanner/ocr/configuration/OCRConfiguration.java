package com.wfiis.receiptscanner.ocr.configuration;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OCRConfiguration {

    @Bean
    public ITesseract myService() {
        Tesseract tesseractInstance = new Tesseract();

        String tessDataFolder = getTesseractDataPath();
        tesseractInstance.setDatapath(tessDataFolder);
        tesseractInstance.setLanguage("eng");
        tesseractInstance.setTessVariable("load_system_dawg", "F");
        tesseractInstance.setTessVariable("load_freq_dawg", "F");
//        tesseractInstance.setPageSegMode(10);

//        tesseractInstance.setTessVariable("tessedit_write_images", "T");

        return tesseractInstance;
    }

    private String getTesseractDataPath() {
        return LoadLibs.extractTessResources("tessdata").getAbsolutePath();
    }

}
