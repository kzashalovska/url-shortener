package com.smartfoxpro.urlshortener.service;

import com.smartfoxpro.urlshortener.dto.UrlRequestDto;
import com.smartfoxpro.urlshortener.entity.Url;
import com.smartfoxpro.urlshortener.repository.UrlRepository;
import com.smartfoxpro.urlshortener.utils.BaseAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.net.URI;

@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    private UrlRepository repository;
    @Autowired
    private BaseAlgorithm conversion;

    //convert to short url according to base62 algorithm
    public String convertToShortUrl(UrlRequestDto request) {
        Url url = repository.getByLongUrl(request.getLongUrl()).orElse(new Url());
        url.setLongUrl(request.getLongUrl());
        repository.save(url);

        return conversion.encode(url.getId());
    }

    //find origin url by short url and return it
    public URI getUri(String shortUrl) {
        long id = conversion.decode(shortUrl);
        Url entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        return URI.create(entity.getLongUrl());
    }
}
