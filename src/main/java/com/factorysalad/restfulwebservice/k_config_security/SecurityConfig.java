package com.factorysalad.restfulwebservice.k_config_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // jdbc를 이용하여 로그인을 구현 할 수 있다.
        //auth.jdbcAuthentication();
        // 메모리 기반의 로그인을 구현
        auth.inMemoryAuthentication()
                .withUser("twotone")
                // 인코딩 없이 사용하기 위한 {noop} 실제 사용 시에는 적절한 알고리즘을 적용하여 사용한다.
                .password("{noop}twotone365")
                // 유저권한을 부여한다.
                .roles("USER");
    }
}
