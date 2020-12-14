package com.example.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class ConvertService {

    private final String BASE62_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int BASE62 = 62;

    public String encoding(long param) {
        StringBuffer sb = new StringBuffer();
        while(param > 0) {
            sb.append(BASE62_CHAR.charAt((int) (param % BASE62)));
            param /= BASE62;
        }
        return sb.toString();
    }

    public Long decoding(String param) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += BASE62_CHAR.indexOf(param.charAt(i)) * power;
            power *= BASE62;
        }
        return sum;
    }

    public String encoder(String id) {
        String shortenedUrl = encoding(Integer.valueOf(id));
        return shortenedUrl;
    }

    public Long decoder(String encoded) {
        long decoded = decoding(encoded);
        return decoded;
    }
}
