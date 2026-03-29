package com.urlshortener.url_shortener.service.impl;

import com.urlshortener.url_shortener.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;


@Service
public class UrlServiceImpl implements UrlService {
    private final Map<String, String> storage = new HashMap<>();
    
    @Override
    public String shorten(String longUrl) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        storage.put(code, longUrl);
        return code;
    }

    @Override
    public String getOriginal(String code) {
        return storage.get(code);
    }
}