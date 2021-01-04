package com.smartfoxpro.urlshortener.service;

import com.smartfoxpro.urlshortener.dto.UrlRequestDto;
import com.smartfoxpro.urlshortener.entity.Url;
import com.smartfoxpro.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@MockBean(UrlRepository.class)
public class UrlServiceImplTest {

    private static final String SHORT_URL = "b";
    @Autowired
    private UrlRepository repository;
    @Autowired
    private UrlService service;

    private static UrlRequestDto urlRequestDto;
    private Url url;

    private static final String TEST_LONG_URL = "https://testLongUrl";

    @BeforeAll
    public static void initAll() {
        urlRequestDto = new UrlRequestDto();
        urlRequestDto.setLongUrl(TEST_LONG_URL);
    }

    @BeforeEach
    public void initEach() {
        url = new Url();
        url.setLongUrl(urlRequestDto.getLongUrl());
        url.setId(1);
    }

    @Test
    public void convertToShortUrl_shouldReturnShortUrl() {
        //given
        when(repository.getByLongUrl(urlRequestDto.getLongUrl())).thenReturn(Optional.of(url));
        when(repository.save(url)).thenReturn(null);

        //when
        service.convertToShortUrl(urlRequestDto);

        //then
        assertEquals(SHORT_URL, service.convertToShortUrl(urlRequestDto));
    }

    @Test
    public void getUrl_shouldReturnLongUrl() {
        //given
        when(repository.findById(url.getId())).thenReturn(Optional.of(url));

        //when
        service.getUri(SHORT_URL);

        //then
        assertEquals(service.getUri(SHORT_URL), URI.create(url.getLongUrl()));
    }
}
