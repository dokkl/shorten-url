package com.hoon.spring.service.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by babybong on 2018. 1. 17..
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ShortenUrlAlgorithmBase62ImplTest {

    @InjectMocks
    ShortenUrlAlgorithmBase62Impl shortenUrlAlgorithmBase62;

    @Mock
    ShortenUrlAlgorithmBase62Impl shortenUrlAlgorithmBase62Mcok;

    @Test
    public void method_검증_test() {
        shortenUrlAlgorithmBase62Mcok.encode(1000000001L);
        shortenUrlAlgorithmBase62Mcok.decode("shortenUrl");

        verify(shortenUrlAlgorithmBase62Mcok).encode(1000000001L);
        verify(shortenUrlAlgorithmBase62Mcok).decode("shortenUrl");
    }

    @Test
    public void encodeAndDecode_test1() {
        String encodedValue = shortenUrlAlgorithmBase62.encode(1234512345L);
        long key = shortenUrlAlgorithmBase62.decode(encodedValue);
        assertEquals(1234512345L, key);
    }

    @Test
    public void encodeAndDecode_test2() {
        String encodedValue = shortenUrlAlgorithmBase62.encode(1000000001L);
        long key = shortenUrlAlgorithmBase62.decode(encodedValue);
        assertEquals(1000000001, key);
    }
}
