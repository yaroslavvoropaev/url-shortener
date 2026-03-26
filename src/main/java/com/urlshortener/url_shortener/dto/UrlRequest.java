package com.urlshortener.url_shortener.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
public class UrlRequest {
    @NotBlank(message = "The URL cannot be empty")
    public String url;
}
