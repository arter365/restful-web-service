package com.factorysalad.restfulwebservice.c_helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // 1) String을 반환하는 rest
    // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    // 2) Bean을 json으로 반환하는 rest
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }

    // 3) path variable
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
