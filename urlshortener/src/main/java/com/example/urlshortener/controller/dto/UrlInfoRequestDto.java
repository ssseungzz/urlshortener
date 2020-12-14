package com.example.urlshortener.controller.dto;

import com.example.urlshortener.domain.UrlInfo;
import lombok.*;

@Data
public class UrlInfoRequestDto {

    private String originUrl;

    @Builder
    public UrlInfoRequestDto(String originUrl) {
        this.originUrl = originUrl;
    }

}
