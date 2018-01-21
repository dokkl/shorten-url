package com.hoon.spring.service.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by babybong on 2018. 1. 17..
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ShortenUrlAlgorithmBase32ImplTest {

    @InjectMocks
    ShortenUrlAlgorithmBase32Impl shortenUrlAlgorithmBase32;

    @Mock
    ShortenUrlAlgorithmBase32Impl shortenUrlAlgorithmBase32Mcok;

    @Test
    public void method_검증_test() {
        shortenUrlAlgorithmBase32Mcok.encode(1000000001L);
        shortenUrlAlgorithmBase32Mcok.decode("shortenUrl");

        verify(shortenUrlAlgorithmBase32Mcok).encode(1000000001L);
        verify(shortenUrlAlgorithmBase32Mcok).decode("shortenUrl");
    }

    @Test
    public void encodeAndDecode_test1() {
        String encodedValue = shortenUrlAlgorithmBase32.encode(1234512345L);
        long key = shortenUrlAlgorithmBase32.decode(encodedValue);
        assertEquals(1234512345L, key);
    }

    @Test
    public void encodeAndDecode_test2() {
        String encodedValue = shortenUrlAlgorithmBase32.encode(1000000001L);
        long key = shortenUrlAlgorithmBase32.decode(encodedValue);
        assertEquals(1000000001, key);
    }
}
