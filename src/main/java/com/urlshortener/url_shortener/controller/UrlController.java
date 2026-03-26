package com.urlshortener.url_shortener.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.urlshortener.url_shortener.service.UrlService;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public String create(@RequestBody String longUrl) {
        System.out.println("DEBUG: Пришла ссылка -> [" + longUrl + "]"); // Это появится в консоли IDEA
        String code = urlService.shorten(longUrl);
        return "http://localhost:8080/" + code + "\n";
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String originalUrl = urlService.getOriginal(code);

        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }
}