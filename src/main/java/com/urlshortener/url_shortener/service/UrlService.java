package com.urlshortener.url_shortener.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


@Service
public class UrlService {
    private final Map<String, String> storage = new HashMap<>();

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

    public String getOriginal(String code) {
        return storage.get(code);
    }
}