package com.smartfoxpro.urlshortener.controller;

import com.smartfoxpro.urlshortener.dto.UrlRequestDto;
import com.smartfoxpro.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    //Converts long url to short url
    @PostMapping("/create-short")
    public String convertToShortUrl(@RequestBody UrlRequestDto request) {
        return urlService.convertToShortUrl(request);

    }

    //redirects to origin url
    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        URI uri = urlService.getUri(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(uri)
                .build();
    }
}
