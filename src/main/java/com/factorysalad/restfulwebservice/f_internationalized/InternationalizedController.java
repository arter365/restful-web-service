package com.factorysalad.restfulwebservice.f_internationalized;

import com.factorysalad.restfulwebservice.c_helloworld.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/*
    1) main -> localeResolver()
    2) application.yml -> 다국어파일의 이름을 지정.
    3) resources -> messages.properties 생성
    4) InternationalizedController
 */

@RestController
public class InternationalizedController {

    @Autowired
    private MessageSource messageSource;

    // 다국어 처리 step4 : (locale정보를 Header로 전달한다. 값이 없으면 디펄트값 Locale.KOREA를 사용한다.)
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
