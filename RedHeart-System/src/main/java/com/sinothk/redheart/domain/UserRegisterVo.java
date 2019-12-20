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

    // 用户类型：-2：管理员；-1，系统；0，普通用户，1. 普通会员
    private Integer userType;
}
