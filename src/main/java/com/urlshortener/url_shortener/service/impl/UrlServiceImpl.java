package com.urlshortener.url_shortener.service.impl;

import com.urlshortener.url_shortener.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


@Service
public class UrlServiceImpl implements UrlService {
    private final Map<String, String> storage = new HashMap<>();



    @Override
    public String shorten(String longUrl) {
        String cleanUrl = longUrl.trim().replace("\"", "");
        if (cleanUrl.endsWith("=")) { //костыль для сurl
            cleanUrl = cleanUrl.substring(0, cleanUrl.length() - 1);
        }

        try {
            cleanUrl = URLDecoder.decode(cleanUrl, StandardCharsets.UTF_8);
        } catch (Exception ignored) { }

        String code = UUID.randomUUID().toString().substring(0, 6);
        storage.put(code, cleanUrl);
        return code;
    }

    @Override
    public String getOriginal(String code) {
        return storage.get(code);
    }
}