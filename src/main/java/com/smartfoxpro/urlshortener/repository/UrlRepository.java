package com.smartfoxpro.urlshortener.repository;

import com.smartfoxpro.urlshortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> getByLongUrl(String url);
}
