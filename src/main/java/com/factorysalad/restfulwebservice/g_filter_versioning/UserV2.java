package com.factorysalad.restfulwebservice.g_filter_versioning;

import com.factorysalad.restfulwebservice.d_restapi.User;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @JsonIgnoreProperties(value={"password","ssn"})
// g_filter : 필터명은 사용자가 임의로 정한다. (필터를 지정하면 기존에 필터없이 호출하던 내용은 에러가 발생한다)
@JsonFilter("UserInfoV2")
public class UserV2 extends User {
    private String grade;
}
