package com.hoon.spring.service.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by babybong on 2018. 1. 16..
 */
@Slf4j
@Component
@Qualifier("algorithmBase32")
public class ShortenUrlAlgorithmBase32Impl implements ShortenUrlAlgorithm {
    /**
     * BASE32 Character Table
     */
    static final char[] BASE32 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();

    @Override
    public String encode(Long value) {
        final StringBuilder sb = new StringBuilder();
        do {
            int i = (int)(value % 62);
            sb.append(BASE32[i]);
            log.info("32encode : {} , sb : {} : {} : {}", String.format("%10d", value), String.format("%2d", i), BASE32[i], sb.toString());
            value /= 32;
        } while (value > 0);
        return sb.toString();
    }

    @Override
    public Long decode(String value) {
        long result = 0;
        long power = 1;
        for (int i = 0; i < value.length(); i++) {
            int digit = new String(BASE32).indexOf(value.charAt(i));
            result += digit * power;
            log.info("32decode : {} : {} : {}", value.charAt(i), String.format("%2d", digit), String.format("%10d", result));
            power *= 32;
        }
        return result;
    }


}
