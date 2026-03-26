package com.urlshortener.url_shortener.service;

public interface UrlService {
    String shorten(String longUrl);
    String getOriginal(String code);
}
