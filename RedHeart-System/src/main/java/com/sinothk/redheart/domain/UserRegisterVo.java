package com.sinothk.redheart.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRegisterVo {

    private String email;
    private String userPwd;
    //
    private Integer sex;
}
