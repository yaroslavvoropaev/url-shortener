package com.urlshortener.url_shortener.dto;


import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class UrlResponse {
    private final String shortUrl;
    private final String originalUrl;
}