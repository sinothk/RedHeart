package com.sinothk.redheart.domain;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "微信用户值")
@Data
@ToString
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
}
