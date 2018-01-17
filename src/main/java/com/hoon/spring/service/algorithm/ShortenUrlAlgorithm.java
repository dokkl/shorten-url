package com.hoon.spring.service.algorithm;

/**
 * 변환 알고리즘 인터페이스
 *
 * Created by babybong on 2018. 1. 16..
 */
public interface ShortenUrlAlgorithm {
    String encode(Long value);

    Long decode(String value);
}
