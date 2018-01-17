package com.hoon.spring.service;

import com.hoon.spring.service.algorithm.ShortenUrlAlgorithm;
import com.hoon.spring.service.repository.ShortenUrl;
import com.hoon.spring.service.repository.ShortenUrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static junit.framework.Assert.assertEquals;

/**
 * Created by babybong on 2018. 1. 17..
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ShortenUrlServiceImplTest {

    @InjectMocks
    private ShortenUrlServiceImpl shortenUrlService;

    @Mock
    @Qualifier("algorithmBase62")
    private ShortenUrlAlgorithm shortenUrlAlgorithm;

    @Mock
    private ShortenUrlRepository shortenUrlRepository;

    private String shortenUrl = "Sq3pFB";
    private Long key = 0L;
    String longUrl = "longUrl";
    ShortenUrl shortenUrlEntity = new ShortenUrl();

    ShortenUrl shortenUrlEntity1 = new ShortenUrl();


    ShortenUrl savedShortenUrlEntity = new ShortenUrl();


    @Before
    public void setup() {
        shortenUrlEntity = new ShortenUrl();
        shortenUrlEntity.setOriginUrl("http://coupang/com/1234567890");

        shortenUrlEntity1.setOriginUrl(longUrl);

        savedShortenUrlEntity.setOriginUrl(longUrl);
        savedShortenUrlEntity.setId(0L);

    }

    @Test
    public void findShortenUrl_test() {

        //when
        doReturn(shortenUrlEntity).when(shortenUrlRepository).findByOriginUrl(longUrl);

        ShortenUrl shortenUrlEntity1 = new ShortenUrl();
        shortenUrlEntity1.setOriginUrl(longUrl);
        doReturn(shortenUrlEntity).when(shortenUrlRepository).save(shortenUrlEntity1);

        doReturn(shortenUrl).when(shortenUrlAlgorithm).encode(shortenUrlEntity.getId());

        //then
        assertEquals(shortenUrlEntity.getShortenUrl(), shortenUrlService.findShortenUrl(longUrl));
    }

    @Test
    public void findShortenUrl_DB에_originUrl이_없는경우_test() {

        //when
        //shortenUrlEntity = null; //DB에_originUrl이_없는경우
        //doReturn(shortenUrlEntity).when(shortenUrlRepository).findByOriginUrl(longUrl);

        //when(shortenUrlRepository.save(shortenUrlEntity1)).thenReturn(savedShortenUrlEntity);
        doReturn(savedShortenUrlEntity).when(shortenUrlRepository).save(any(ShortenUrl.class));

        doReturn(shortenUrl).when(shortenUrlAlgorithm).encode(savedShortenUrlEntity.getId());
        savedShortenUrlEntity.setShortenUrl(shortenUrl);

        //then
        assertEquals(savedShortenUrlEntity.getShortenUrl(), shortenUrlService.findShortenUrl(longUrl));
    }

    @Test
    public void findOriginUrl_test() {

        //when
        //doReturn(key).when(shortenUrlAlgorithm).decode(shortenUrl);
        doReturn(shortenUrlEntity).when(shortenUrlRepository).findOne(any(Long.class));

        //then
        assertEquals(shortenUrlEntity.getOriginUrl(), shortenUrlService.findOriginUrl(shortenUrl));
    }
}
