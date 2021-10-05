package com.factorysalad.restfulwebservice.d_restapi;

import com.factorysalad.restfulwebservice.e_exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/*
- 모든 사용자 검색		    - GET 	    /users
- 사용자 생성		        - POST	    /users
- 한 명의 사용자 검색		- GET	    /users/{id} -> /users/1
- 사용자 삭제		        - DELETE 	/users/{id}	-> /users/1

- 사용자의 모든 게시물 검색	- GET	    /users/{id}/posts
- 사용자 게시물 작성		- POST	    /users/{id}/posts
- 게시물 세부 정보 검색	- GET	    /users/{id}/posts/{post_id}
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // 주소줄에 내용이 오기 때문에 @PathVariable
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null)
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        return user;
    }

    // body에 내용이 오기 때문에 @RequestBody
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        // 이 구문만 있으면 쓰기 요청에 status=200으로 전달된다. (그냥 성공을 의미함)
        User savedUser = service.save(user);

        /*
        1) 요청에 대한 status 코드
        Rest API 구현 시 성공은 무조건 status=200이다.
        하지만 쓰기에 성공 했을 시 status=201이다.
        2) 헤더에 특정 값을 포함한 URI 전달
        요청이 있은 후 경로를 사용자에게 전달 또는 파일업로드 후 다운로드 주소 전달 시 사용한다.
         */
        // 요청한 URI 가져와서 path에 원하는 정보 추가 (.path는 여러번 사용가능)
        // 이렇게 하면 헤더에 Location 값이 전달되고 Status=201이 된다.
        // 아래와 같이 주소값을 만들어서 전달한다.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user == null)
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }
}
