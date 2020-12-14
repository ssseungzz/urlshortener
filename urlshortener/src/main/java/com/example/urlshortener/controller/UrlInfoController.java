package com.example.urlshortener.controller;

import com.example.urlshortener.controller.dto.UrlInfoRequestDto;
import com.example.urlshortener.reponse.Response;
import com.example.urlshortener.reponse.ResponseCode;
import com.example.urlshortener.reponse.ResponseMessage;
import com.example.urlshortener.reponse.UrlErrorResponse;
import com.example.urlshortener.service.UrlInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UrlInfoController {

    private final UrlInfoService urlInfoService;

    @PostMapping(value = "/api/convert")
    @ResponseBody
    public Response convertToShortenedUrl(UrlInfoRequestDto request) {
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(request.getOriginUrl())) {
            return Response.builder()
                    .responseCode(ResponseCode.SUCCESS)
                    .responseData(urlInfoService.convertToShortenedUrl(request))
                    .build();
        }
        else {
            return Response.builder()
                    .responseCode(ResponseCode.FAIL)
                    .responseData(ResponseMessage.INVALID_URL)
                    .build();
        }
    }

    @GetMapping(value = "/{shortenedUrl}")
    public ResponseEntity<Object> convertToOriginUrl(@PathVariable("shortenedUrl") String shortenedUrl) throws URISyntaxException {
        String url = urlInfoService.convertToOriginUrl(shortenedUrl).getOriginUrl();
        URI redirectUri = new URI(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public UrlErrorResponse urlError(NullPointerException e) {
        return UrlErrorResponse.builder()
                .message("Check shortened again")
                .reason("Invalid shortened url")
                .build();
    }

}
