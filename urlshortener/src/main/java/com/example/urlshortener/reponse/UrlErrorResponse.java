package com.example.urlshortener.reponse;

import lombok.Builder;
import lombok.Data;

@Data
public class UrlErrorResponse {

    private String message;
    private String reason;

    @Builder
    public UrlErrorResponse(String message, String reason) {
        this.message = message;
        this.reason = reason;
    }
}
