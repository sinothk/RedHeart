package com.sinothk.redheart.domain;


import io.swagger.annotations.ApiModel;



@ApiModel(description = "微信用户值")


public class WxUserVo {
//    avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/pbjN1D7TjYHk15OQMeUicnykRNDuOybX2KGemofkRGBLIPdJo4V8y2vFePMnia02HBVvibMOvwaoyHdqrpnaqBhIw/132"
//    city: "Guiyang"
//    country: "China"
//    gender: 1
//    language: "zh_CN"
//    nickName: "o O"
//    province: "Guizhou"

    private String openid;
    private String avatarUrl;
    private String country;
    private String province;
    private String city;
    private String language;
    private String nickName;
    private Integer gender; // 0：位置；1：男0；2：女

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
