package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel(description = "用户AO信息")
@Data
@ToString
public class UserAO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "账号")
    private Long account;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "电话号码")
    private String phoneNum;

    @ApiModelProperty(value = "用户状态")
    private Integer userStatus;

    @ApiModelProperty(value = "用户生日")
    private Date userBorthday;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "用户家乡")
    private String hometown;

    @ApiModelProperty(value = "用户类型：-1：系统；0、普通用户，1. 普通会员")
    private Integer userType;

    @ApiModelProperty(value = "登录纬度")
    private Double loginLat;

    @ApiModelProperty(value = "登录经度")
    private Double loginLon;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "登录位置")
    private String loginAddress;

    @ApiModelProperty(value = "登录省份")
    private String loginProvince;

    @ApiModelProperty(value = "登录城市")
    private String loginCity;

    @ApiModelProperty(value = "登录区县")
    private String loginDistrict;

    // ==================================================
    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "权限")
    private String permission;

    @ApiModelProperty(value = "IMEI")
    private String imei;

    @ApiModelProperty(value = "距离")
    private Double distance;
}
