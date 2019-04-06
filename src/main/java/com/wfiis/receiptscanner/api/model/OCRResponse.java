package com.wfiis.receiptscanner.api.model;

import java.util.List;

public class OCRResponse {

    private List<Product> products;
    private long timeOfExecution;

    public long getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecution(long timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }
}
