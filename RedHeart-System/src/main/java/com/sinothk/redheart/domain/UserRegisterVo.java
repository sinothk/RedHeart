package com.sinothk.redheart.domain;






public class UserRegisterVo {

    private String email;
    private String userPwd;
    //
    private Integer sex;

    // 用户类型：-2：管理员；-1，系统；0，普通用户，1. 普通会员
    private Integer userType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
