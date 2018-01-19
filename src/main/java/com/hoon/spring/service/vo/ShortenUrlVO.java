package com.hoon.spring.service.vo;

import com.hoon.spring.service.repository.ShortenUrl;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by babybong on 2018. 1. 19..
 */
@Getter
@Setter
@ToString
public class ShortenUrlVO {
    private Long id;
    private String shortenUrl;
    private String originUrl;
    private Date createdAt;
    private Date modifiedAt;

    public static ShortenUrlVO convertToVO(ShortenUrl shortenUrl) {
        ShortenUrlVO vo = new ShortenUrlVO();
        vo.id = shortenUrl.getId();
        vo.shortenUrl = shortenUrl.getShortenUrl();
        vo.originUrl = shortenUrl.getOriginUrl();
        vo.createdAt = shortenUrl.getCreatedAt();
        vo.modifiedAt = shortenUrl.getModifiedAt();
        return vo;
    }

    public ShortenUrlVO add(String serverDoamin) {
        this.shortenUrl = serverDoamin + this.getShortenUrl();
        return this;
    }
}
