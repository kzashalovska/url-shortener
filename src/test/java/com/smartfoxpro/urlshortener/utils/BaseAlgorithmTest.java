package com.smartfoxpro.urlshortener.utils;

import com.smartfoxpro.urlshortener.dto.UrlRequestDto;
import com.smartfoxpro.urlshortener.entity.Url;
import com.smartfoxpro.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@MockBean(UrlRepository.class)
public class BaseAlgorithmTest {

    private BaseAlgorithm algorithm = new BaseAlgorithm();
    private static final String SHORT_URL = "b";
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
    public void Decode_shouldReturnIdByShortUrl() {
        //given
        //when

        //then
        assertEquals(url.getId(), algorithm.decode(SHORT_URL));
    }
}
