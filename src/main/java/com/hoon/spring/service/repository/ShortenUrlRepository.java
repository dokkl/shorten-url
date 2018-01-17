package com.hoon.spring.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by babybong on 2018. 1. 16..
 */
@Repository
public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {
    ShortenUrl findByOriginUrl(String originUrl);
}
