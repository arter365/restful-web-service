package com.factorysalad.restfulwebservice.i_config_swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 연락처 정보를 추가한다.
    private static final Contact DEFAULT_CONTACT = new Contact("Twotone", "http://twotone.co.kr", "ljs@twotone.co.kr");
    // API 정보를 추가한다.
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Twotone API Title", "My User management REST API service", "1.0",
            "urn:twotone",DEFAULT_CONTACT, "Twotone License", "http://twotone.co.kr/license", new ArrayList<>());
    // 전달하는 형식을 추가한다.
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json", "application/xml"));

    // SpringBoot2.2 이상에서 Swagger가 추가될 때 HATEOS 에러가 발생하는데 아래의 메서드를 추가한다.
    @Primary
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    // Docket이라는 형태의 문서를 반환한다. 메서드명은 사용자 정의이다.
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 위에서 추가한 연락처, API, 형식 정보를 추가한다.
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
