package com.hoon.spring.service;

import com.hoon.spring.service.algorithm.ShortenUrlAlgorithm;
import com.hoon.spring.service.exception.ShortenUrlNotFoundException;
import com.hoon.spring.service.repository.ShortenUrl;
import com.hoon.spring.service.repository.ShortenUrlRepository;
import com.hoon.spring.service.vo.ShortenUrlVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.Map;

/**
 * ShortenUrlService 구현체
 *
 * Created by babybong on 2018. 1. 16..
 */
@Slf4j
@Service
public class ShortenUrlServiceImpl implements ShortenUrlService {

    @Autowired
    private Map<String, ShortenUrlAlgorithm> algorithmMap;

    private ShortenUrlAlgorithm shortenUrlAlgorithm;

    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Value("${hoon.server.domain}")
    private String serverDomain;

    @Value("${hoon.strategy.algrithm}")
    private String strategyAlgorithm;

    @PostConstruct
    public void init() {
        log.info("service init strategyAlgorithm : [{}]", strategyAlgorithm);
        if (StringUtils.isEmpty(strategyAlgorithm)) {
            shortenUrlAlgorithm = algorithmMap.get("algorithmBase62"); //잔략 살정이 없으면 기본으로 base62 사용
        } else {
            shortenUrlAlgorithm = algorithmMap.get(strategyAlgorithm);
        }
        log.info("service init shortenUrlAlgorithm : {}", shortenUrlAlgorithm);
    }

    @Transactional(readOnly = false)
    public ShortenUrlVO findShortenUrl(String longUrl) {
        //longUrl을 DB에서 찾는다. 없으면 생성한다.
        ShortenUrl existShortenUrlEntity = shortenUrlRepository.findByOriginUrl(longUrl);
        if (existShortenUrlEntity != null) {
            return ShortenUrlVO.convertToVO(existShortenUrlEntity).add(getServerDomain());
        }

        ShortenUrl shortenUrlEntity = new ShortenUrl();
        shortenUrlEntity.setOriginUrl(longUrl);
        shortenUrlEntity.setCreatedAt(new Date());
        ShortenUrl savedShortenUrlEntity = shortenUrlRepository.save(shortenUrlEntity);

        String encodedUrl = shortenUrlAlgorithm.encode(savedShortenUrlEntity.getId());
        savedShortenUrlEntity.setShortenUrl(encodedUrl);
        savedShortenUrlEntity.setModifiedAt(new Date());
        return ShortenUrlVO.convertToVO(savedShortenUrlEntity).add(getServerDomain());
    }

    @Transactional(readOnly = true)
    public String findOriginUrl(String shortenUrl) {
        Long decodedkey = shortenUrlAlgorithm.decode(shortenUrl);
        ShortenUrl shortenUrlEntity = shortenUrlRepository.findOne(decodedkey);
        log.info("shortenUrlEntity : {} : {} : {}", shortenUrl, decodedkey, shortenUrlEntity);
        if (shortenUrlEntity == null) {
            throw new ShortenUrlNotFoundException("요청한 shorten URL이 존재하지 않습니다");
        }
        return shortenUrlEntity.getOriginUrl();
    }

    private String getServerDomain() {
        if (StringUtils.isEmpty(serverDomain)) {
            return "http://localhost:8080/";
        }
        return serverDomain;
    }
}
