package com.wfiis.receiptscanner.config;

import com.wfiis.receiptscanner.efficiency.util.MultipartFileLoader;
import com.wfiis.receiptscanner.efficiency.util.TextFromFilesProvider;
import com.wfiis.receiptscanner.ocr.TextRecognizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public TextFromFilesProvider sumTextFromFileProvider(TextRecognizer textRecognizer, MultipartFileLoader multipartFileLoader) {
        String suffix = "";
        return new TextFromFilesProvider(suffix, textRecognizer, multipartFileLoader);
    }

    @Bean
    public TextFromFilesProvider dateTextFromFileProvider(TextRecognizer textRecognizer, MultipartFileLoader multipartFileLoader) {
        String suffix = "_DATE_EXPECTED";
        return new TextFromFilesProvider(suffix, textRecognizer, multipartFileLoader);
    }

}
