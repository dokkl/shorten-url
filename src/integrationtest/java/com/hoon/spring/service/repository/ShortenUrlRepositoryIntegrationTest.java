package com.hoon.spring.service.repository;

import com.hoon.spring.infra.test.IntegrationTest;
import com.hoon.spring.service.ShortenUrlService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.assertEquals;

/**
 * ShortenUrlRepository 인테그레이션 테스트
 *
 * Created by babybong on 2018. 1. 17..
 */
@Slf4j
public class ShortenUrlRepositoryIntegrationTest extends IntegrationTest {
    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    private String originUrl = null;

    @Before
    public void setup() {
        originUrl = "http://www.coupang.com/np/goldbox";
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(true)
    public void save_test() {
        ShortenUrl shortenUrlEntity = new ShortenUrl();
        shortenUrlEntity.setOriginUrl(originUrl);
        ShortenUrl savedShortenUrl = shortenUrlRepository.save(shortenUrlEntity);
        log.info("savedShortenUrl : {}", savedShortenUrl);
        assertEquals(originUrl, savedShortenUrl.getOriginUrl());
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(true)
    public void saveAndUpdate_test() {
        ShortenUrl shortenUrlEntity = new ShortenUrl();
        shortenUrlEntity.setOriginUrl(originUrl);
        ShortenUrl savedShortenUrl = shortenUrlRepository.save(shortenUrlEntity);
        savedShortenUrl.setShortenUrl("SHORTEN");

        Long id = savedShortenUrl.getId();
        ShortenUrl shortenUrl = shortenUrlRepository.findOne(id);

        assertEquals(id, shortenUrl.getId());
        assertEquals("SHORTEN", shortenUrl.getShortenUrl());
        assertEquals(originUrl, shortenUrl.getOriginUrl());
    }

    @Test
    @Transactional(readOnly = false)
    @Rollback(true)
    public void findByOriginUrl_test() {
        ShortenUrl shortenUrlEntity = new ShortenUrl();
        shortenUrlEntity.setOriginUrl(originUrl);
        shortenUrlRepository.save(shortenUrlEntity);

        ShortenUrl shortenUrl = shortenUrlRepository.findByOriginUrl(originUrl);
        assertEquals(originUrl, shortenUrl.getOriginUrl());
    }
}
