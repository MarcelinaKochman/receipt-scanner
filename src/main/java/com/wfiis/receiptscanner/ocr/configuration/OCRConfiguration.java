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
        ITesseract tesseractInstance = new Tesseract();

        String tessDataFolder = getTesseractDataPath();
        tesseractInstance.setDatapath(tessDataFolder);

        return tesseractInstance;
    }

    private String getTesseractDataPath() {
        return LoadLibs.extractTessResources("tessdata").getAbsolutePath();
    }

}
