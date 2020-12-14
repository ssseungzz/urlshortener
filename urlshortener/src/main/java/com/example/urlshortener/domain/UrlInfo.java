package com.example.urlshortener.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class UrlInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String originUrl;

    @Setter
    private String shortenedUrl;

    @Builder
    public UrlInfo(String shortenedUrl, String originUrl) {
        this.shortenedUrl = shortenedUrl;
        this.originUrl = originUrl;
    }

}
