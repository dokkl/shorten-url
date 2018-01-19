package com.hoon.spring.service;

import com.hoon.spring.service.vo.ShortenUrlVO;

/**
 * ShortenUrl 서비스
 *
 * Created by babybong on 2018. 1. 16..
 */
public interface ShortenUrlService {
    /**
     * 원래의 URL 을 shortenUrl VO객체로 반환하는 메소드
     * @param longUrl
     * @return
     */
    ShortenUrlVO findShortenUrl(String longUrl);

    /**
     * shorten URL 로 원래의 URL을 반환하는 메소드
     * @param shortenUrl
     * @return
     */
    String findOriginUrl(String shortenUrl);
}
