package com.hoon.spring.service;

import com.hoon.spring.infra.test.IntegrationTest;
import com.hoon.spring.service.exception.ShortenUrlNotFoundException;
import com.hoon.spring.service.vo.ShortenUrlVO;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 * Created by babybong on 2018. 1. 21..
 */
@Slf4j
public class ShortenUrlServiceIntegrationTest extends IntegrationTest {
    @Autowired
    private ShortenUrlService shortenUrlService;

    @Test
    public void findShortenUrl_test() {
        String longUrl = "https://spring.io/guides/gs/register-twitter-app/";
        ShortenUrlVO shortenUrlVO = shortenUrlService.findShortenUrl(longUrl);

        assertNotNull(shortenUrlVO);
        log.info("shortenUrlVO : {}", shortenUrlVO);
    }

    @Test(expected = ShortenUrlNotFoundException.class)
    public void findOriginUrl_test() {
        String shortenUrl = "Rq3pFB";
        String originUrl = shortenUrlService.findOriginUrl(shortenUrl);
    }
}
