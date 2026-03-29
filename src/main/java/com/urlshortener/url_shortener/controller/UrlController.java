package com.urlshortener.url_shortener.controller;


import com.urlshortener.url_shortener.dto.UrlRequest;
import com.urlshortener.url_shortener.dto.UrlResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.urlshortener.url_shortener.service.impl.UrlServiceImpl;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlServiceImpl urlServiceImpl;


    public UrlController(UrlServiceImpl urlServiceImpl) {
        this.urlServiceImpl = urlServiceImpl;
    }


    @PostMapping("/shorten")
    public UrlResponse create(@Valid @RequestBody UrlRequest request) {
        String code = urlServiceImpl.shorten(request.getUrl());
        String shortUrl = "http://localhost:8080/" + code;
        return new UrlResponse(shortUrl, request.getUrl());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String originalUrl = urlServiceImpl.getOriginal(code);

        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }
}