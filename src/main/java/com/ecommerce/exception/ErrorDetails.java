package com.ecommerce.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private String error;
    private String details;
    private LocalDateTime timeStamp;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ErrorDetails() {
    }

    public ErrorDetails(String error, String details, LocalDateTime timeStamp) {
        this.error = error;
        this.details = details;
        this.timeStamp = timeStamp;
    }
}
