package com.hoon.spring.service.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by babybong on 2018. 1. 16..
 */
@Slf4j
@Component
@Qualifier("algorithmBase62")
public class ShortenUrlAlgorithmBase62Impl implements ShortenUrlAlgorithm {
    /**
     * BASE62 Character Table
     */
    static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    static final int NUMBER_62 = 62;

    @Override
    public String encode(Long value) {
        final StringBuilder sb = new StringBuilder();
        do {
            int i = (int)(value % NUMBER_62);
            sb.append(BASE62[i]);
            log.info("62encode : {} , sb : {} : {} : {}", String.format("%10d", value), String.format("%2d", i), BASE62[i], sb.toString());
            value /= NUMBER_62;
        } while (value > 0);
        return sb.toString();
    }

    @Override
    public Long decode(String value) {
        long result = 0;
        long power = 1;
        for (int i = 0; i < value.length(); i++) {
            int digit = new String(BASE62).indexOf(value.charAt(i));
            result += digit * power;
            log.info("62decode : {} : {} : {}", value.charAt(i), String.format("%2d", digit), String.format("%10d", result));
            power *= NUMBER_62;
        }
        return result;
    }
}
