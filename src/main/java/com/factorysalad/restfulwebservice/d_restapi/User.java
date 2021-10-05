package com.factorysalad.restfulwebservice.d_restapi;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
자바빈(POJO) 유효성 검사 시 의존성 추가 https://www.baeldung.com/javax-validation
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
	<version>6.2.0.Final</version>
</dependency>

controller에 @Valid 추가
 */
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @JsonIgnoreProperties(value={"password","ssn"})
// g_filter : 필터명은 사용자가 임의로 정한다. (필터를 지정하면 기존에 필터없이 호출하던 내용은 에러가 발생한다)
@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {
    private Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;
    // 과거 날짜만 올 수 있다.
    @Past
    @ApiModelProperty(notes = "사용자의 생일을 입력해 주세요.")
    private Date birthDate;

    @ApiModelProperty(notes = "사용자 패스워드를 입력해 주세요.")
    private String password;

    @ApiModelProperty(notes = "사용자의 주민번호를 입력해 주세요.")
    private String ssn;
}
