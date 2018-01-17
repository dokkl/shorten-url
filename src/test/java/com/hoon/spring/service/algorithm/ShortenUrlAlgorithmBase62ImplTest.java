package com.hoon.spring.service.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import static junit.framework.Assert.assertEquals;

/**
 * Created by babybong on 2018. 1. 17..
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ShortenUrlAlgorithmBase62ImplTest {
    @InjectMocks
    ShortenUrlAlgorithmBase62Impl shortenUrlAlgorithmBase62;

    @Test
    public void encodeToLongAndDecodeToLong_test1() {
        String encodedValue = shortenUrlAlgorithmBase62.encode(1234512345L);
        long key = shortenUrlAlgorithmBase62.decode(encodedValue);
        assertEquals(1234512345, key);
    }

    @Test
    public void encodeToLongAndDecodeToLong_test2() {
        String encodedValue = shortenUrlAlgorithmBase62.encode(1000000001L);
        long key = shortenUrlAlgorithmBase62.decode(encodedValue);
        assertEquals(1000000001, key);
    }
}
