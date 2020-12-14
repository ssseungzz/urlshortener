package com.example.urlshortener.controller.dto;

import com.example.urlshortener.domain.UrlInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UrlInfoResponseDto {
    private Long id;
    private String shortenedUrl;
    private String originUrl;

    @Builder
    public UrlInfoResponseDto(UrlInfo entity) {
        this.id = entity.getId();
        this.shortenedUrl = entity.getShortenedUrl();
        this.originUrl = entity.getOriginUrl();
    }
}
