package com.example.urlshortener.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlInfoRepository extends JpaRepository<UrlInfo, Long> {
    UrlInfo findFirstByShortenedUrl(String shortenedUrl);
    UrlInfo findFirstByOriginUrl(String originUrl);
    boolean existsByOriginUrl(String originUrl);
    boolean existsByShortenedUrl(String shortenedUrl);
}
