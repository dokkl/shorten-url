package com.hoon.spring.service;

import com.hoon.spring.service.algorithm.ShortenUrlAlgorithm;
import com.hoon.spring.service.repository.ShortenUrl;
import com.hoon.spring.service.repository.ShortenUrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ShortenUrlService 구현체
 *
 * Created by babybong on 2018. 1. 16..
 */
@Slf4j
@Service
public class ShortenUrlServiceImpl implements ShortenUrlService {

    @Autowired
    @Qualifier("algorithmBase62")
    private ShortenUrlAlgorithm shortenUrlAlgorithm;

    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Transactional(readOnly = false)
    public String findShortenUrl(String longUrl) {
        //longUrl을 DB에서 찾는다. 없으면 생성한다.
        ShortenUrl existShortenUrlEntity = shortenUrlRepository.findByOriginUrl(longUrl);
        if (existShortenUrlEntity != null) {
            return existShortenUrlEntity.getShortenUrl();
        }

        ShortenUrl shortenUrlEntity = new ShortenUrl();
        shortenUrlEntity.setOriginUrl(longUrl);
        ShortenUrl savedShortenUrlEntity = shortenUrlRepository.save(shortenUrlEntity);

        String encodedUrl = shortenUrlAlgorithm.encode(savedShortenUrlEntity.getId());
        savedShortenUrlEntity.setShortenUrl(encodedUrl);

        return encodedUrl;
    }

    @Transactional(readOnly = true)
    public String findOriginUrl(String shortenUrl) {
        Long decodedkey = shortenUrlAlgorithm.decode(shortenUrl);
        ShortenUrl shortenUrlEntity = shortenUrlRepository.findOne(decodedkey);
        log.info("shortenUrlEntity : {} : {} : {}", shortenUrl, decodedkey, shortenUrlEntity);
        return shortenUrlEntity.getOriginUrl();
    }
}
