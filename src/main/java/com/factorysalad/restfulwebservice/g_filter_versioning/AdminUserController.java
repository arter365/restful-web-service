package com.factorysalad.restfulwebservice.g_filter_versioning;

import com.factorysalad.restfulwebservice.d_restapi.User;
import com.factorysalad.restfulwebservice.d_restapi.UserDaoService;
import com.factorysalad.restfulwebservice.e_exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
테스트를 위해 관리자용 컨트롤러를 만든다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")   // 이렇게 클래스에 적용하면 /admin prefix가 자동으로 붙는다 (/admin/users , /admin/users/{id})
public class AdminUserController {
    private final UserDaoService service;

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        // 필터를 적용한다.
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "birthDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;

    }

    // 주소줄에 내용이 오기 때문에 @PathVariable
    // URI를 사용한 버전관리
    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null)
            throw new UserNotFoundException(String.format("ID[%s] not found", id));

        // 필터를 적용한다.
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "birthDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }

    // 다양한 버전관리 방법 (버전명으로 사용되는 내용은 사용자정의 명칭이다.)
    // 1) URI Versioning (Twitter) : @GetMapping("/v2/users/{id}")
    // 2) Request Parameter Versioning (Amazon) : @GetMapping(value = "/users/{id}/", params = "version=2")
    // 3) Media type Versioning (GitHub) : @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
    // 4) Headers Versioning (Microsoft) : @GetMapping(value = "/users/{id}", produces = "application/vnc.twotone.appv2+json")
    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);

        // BeanUtils를 사용한 복사 : User -> User2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");

        if(user == null)
            throw new UserNotFoundException(String.format("ID[%s] not found", id));

        // 필터를 적용한다.
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "birthDate", "ssn", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
    }
}
