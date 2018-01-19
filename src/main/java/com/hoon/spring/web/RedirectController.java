package com.hoon.spring.web;

import com.hoon.spring.service.ShortenUrlService;
import com.hoon.spring.service.exception.ShortenUrlNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service controller
 *
 * Created by babybong on 2018. 1. 16..
 */
@Slf4j
@Controller
public class RedirectController {
    @Autowired
    private ShortenUrlService shortenUrlService;

    @RequestMapping("/{shortenUrl}")
    public String redirect(@PathVariable String shortenUrl) {
        log.info("shortenUrl : {}", shortenUrl);
        return "redirect:" + shortenUrlService.findOriginUrl(shortenUrl);
    }

    @ExceptionHandler(ShortenUrlNotFoundException.class)
    public String goNotFoundErrorPage(ShortenUrlNotFoundException e, Model model) {
        model.addAttribute("exception", e);
        return "notFound";
    }
}
