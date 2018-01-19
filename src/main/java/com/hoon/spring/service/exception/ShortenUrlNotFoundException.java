package com.hoon.spring.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by babybong on 2018. 1. 18..
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="요청한 shorten URL이 존재하지 않습니다")
public class ShortenUrlNotFoundException extends RuntimeException {
    public ShortenUrlNotFoundException() {
        super();
    }
    public ShortenUrlNotFoundException(String message) {
        super(message);
    }
}
