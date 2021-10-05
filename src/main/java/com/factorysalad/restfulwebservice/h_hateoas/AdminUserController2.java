package com.factorysalad.restfulwebservice.h_hateoas;

import com.factorysalad.restfulwebservice.d_restapi.User;
import com.factorysalad.restfulwebservice.d_restapi.UserDaoService;
import com.factorysalad.restfulwebservice.e_exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin2")
public class AdminUserController2 {
    private final UserDaoService service;

    @GetMapping("/v3/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        // 필터를 적용한다.
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "birthDate", "ssn", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/v3/users/{id}")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);

        //HATEOAS 생성
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));

        if(user == null)
            throw new UserNotFoundException(String.format("ID[%s] not found", id));

        // 필터를 적용한다.
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "birthDate", "ssn", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);
        //HATEOAS 추가
        mapping.setValue(model);
        return mapping;
    }

}
