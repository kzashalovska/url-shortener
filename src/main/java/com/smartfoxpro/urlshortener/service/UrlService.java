package com.smartfoxpro.urlshortener.service;

import com.smartfoxpro.urlshortener.dto.UrlRequestDto;

import java.net.URI;

public interface UrlService {
    String convertToShortUrl(UrlRequestDto request);

    URI getUri(String shortUrl);
}
