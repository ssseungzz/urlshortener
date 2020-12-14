package com.example.urlshortener.service;

import com.example.urlshortener.controller.dto.UrlInfoRequestDto;
import com.example.urlshortener.controller.dto.UrlInfoResponseDto;
import com.example.urlshortener.domain.UrlInfo;
import com.example.urlshortener.domain.UrlInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Slf4j
public class UrlInfoService {

    private final UrlInfoRepository urlInfoRepository;
    private final ConvertService convertService;

    @Transactional
    public UrlInfoResponseDto convertToShortenedUrl(UrlInfoRequestDto request) {

        if (urlInfoRepository.existsByOriginUrl(request.getOriginUrl())) {
            UrlInfo entity = urlInfoRepository.findFirstByOriginUrl(request.getOriginUrl());
            return UrlInfoResponseDto.builder().entity(entity).build();
        }
        else {
            UrlInfo entity = new UrlInfo();
            entity.setOriginUrl(request.getOriginUrl());
            urlInfoRepository.save(entity);

            String encodedUrl = "";
            encodedUrl = encoder(String.valueOf(entity.getId()));
            entity.setShortenedUrl(encodedUrl);

            return UrlInfoResponseDto.builder().entity(entity).build();
        }
    }


    public UrlInfoResponseDto convertToOriginUrl(String shortenedUrl) {
        UrlInfo entity = urlInfoRepository.findFirstByShortenedUrl(shortenedUrl);
        return UrlInfoResponseDto.builder().entity(entity).build();

    }

    private String encoder(String id) {
        return convertService.encoder(id);
    }

    private Long decoder(String shortenedUrl) {
        return convertService.decoder(shortenedUrl);
    }

}
