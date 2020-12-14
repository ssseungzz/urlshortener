package com.example.urlshortener.reponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Response<T> {
    private Integer responseCode;
    private T responseData;

    @Builder
    public Response(Integer responseCode, T responseData) {
        this.responseCode = responseCode;
        this.responseData = responseData;
    }
}
