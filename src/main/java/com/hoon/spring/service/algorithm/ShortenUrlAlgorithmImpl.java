package com.hoon.spring.service.algorithm;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by babybong on 2018. 1. 16..
 */
@Component
@Qualifier("algorithmDevelop")
public class ShortenUrlAlgorithmImpl implements ShortenUrlAlgorithm {
    private final int LENGTH_OF_URL_CODE=6;

    @Override
    public String encode(Long toBeConverted) {
        return null;
    }

    @Override
    public Long decode(String value) {
        return 0L;
    }

}
