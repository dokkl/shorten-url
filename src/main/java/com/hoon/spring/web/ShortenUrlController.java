package com.hoon.spring.web;

import com.hoon.spring.service.ShortenUrlService;
import com.hoon.spring.service.vo.ShortenUrlVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * URL을 입력받아 shorten URL을 돌려주는 서비스 controller
 *
 * Created by babybong on 2018. 1. 15..
 */
@Slf4j
@Controller
public class ShortenUrlController {

    @Autowired
    private ShortenUrlService shortenUrlService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/shortenUrl", method = POST)
    public String createShortenUrl(String longUrl, Model model) {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (!urlValidator.isValid(longUrl)) {
            model.addAttribute("message", "올바른 URL이 아닙니다.");
            return "index";
        }
        if (longUrl.length() > 255) {
            model.addAttribute("message", "URL의 길이가 255 보다 작아야합니다.");
            return "index";
        }

        ShortenUrlVO shortenUrlVO = shortenUrlService.findShortenUrl(longUrl);
        model.addAttribute("shortenUrlVO", shortenUrlVO);
        return "index";
    }
}
