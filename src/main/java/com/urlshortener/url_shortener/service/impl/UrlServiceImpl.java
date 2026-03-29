package com.urlshortener.url_shortener.service.impl;

import com.urlshortener.url_shortener.model.UrlMapping;
import com.urlshortener.url_shortener.repository.UrlRepository;
import com.urlshortener.url_shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    
    @Override
    public String shorten(String longUrl) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        UrlMapping mapping = new UrlMapping(code, longUrl);

        urlRepository.save(mapping);
        return code;
    }

    @Override
    public String getOriginal(String code) {
        return urlRepository.findByCode(code)
                .map(UrlMapping::getOriginalUrl)
                .orElse(null);
    }
}