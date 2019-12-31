package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel(description = "用户信息")
@Data
@ToString
@TableName(value = "tb_comm_user")
public class UserEntity {

//    Field        Type           Collation        Null    Key     Default  Extra           Privileges                       Comment
//-----------  -------------  ---------------  ------  ------  -------  --------------  -------------------------------  --------------
//    id           int(11)        (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  编号
//    u_account    varchar(64)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  账号
//    u_name       varchar(64)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  姓名
//    u_nickname   varchar(64)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  昵称
//    u_avatar     varchar(2048)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  头像
//    u_pwd        varchar(64)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  密码
//    u_email      varchar(64)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  邮箱地址
//    u_sex        decimal(1,0)   (NULL)           YES             0                        select,insert,update,references  性别
//    u_idcard     varchar(32)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  身份证号
//    u_phone_num  varchar(15)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  手机号码

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "账号")
    @TableField("u_account")
    private Long account;

    @ApiModelProperty(value = "姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "昵称")
    @TableField("u_nickname")
    private String nickname;

    @ApiModelProperty(value = "头像")
    @TableField("u_avatar")
    private String avatar;

    @ApiModelProperty(value = "密码")
    @TableField("u_pwd")
    private String userPwd;

    @ApiModelProperty(value = "邮箱")
    @TableField("u_email")
    private String email;

    @ApiModelProperty(value = "性别")
    @TableField("u_sex")
    private Integer sex;

    @ApiModelProperty(value = "身份证")
    @TableField("u_idcard")
    private String idCard;

    @ApiModelProperty(value = "电话号码")
    @TableField("u_phone_num")
    private String phoneNum;

    @ApiModelProperty(value = "用户状态")
    @TableField("user_status")
    private Integer userStatus;

    @ApiModelProperty(value = "用户生日")
    @TableField("user_borthday")
    private Date userBorthday;

    @ApiModelProperty(value = "注册时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "用户家乡")
    @TableField("user_hometown")
    private String hometown;

    @ApiModelProperty(value = "用户类型：-1：系统；0、普通用户，1. 普通会员")
    @TableField("user_type")
    private Integer userType;

    @ApiModelProperty(value = "登录纬度")
    @TableField("login_lat")
    private Double loginLat;

    @ApiModelProperty(value = "登录经度")
    @TableField("login_lon")
    private Double loginLon;

    @ApiModelProperty(value = "登录时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty(value = "登录位置")
    @TableField("login_address")
    private String loginAddress;

    @ApiModelProperty(value = "登录省份")
    @TableField("login_province")
    private String loginProvince;

    @ApiModelProperty(value = "登录城市")
    @TableField("login_city")
    private String loginCity;

    @ApiModelProperty(value = "登录区县")
    @TableField("login_district")
    private String loginDistrict;

    // ==================================================
    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    private String role;

    @ApiModelProperty(value = "权限")
    @TableField(exist = false)
    private String permission;

    @ApiModelProperty(value = "Token")
    @TableField(exist = false)
    private String token;

    @ApiModelProperty(value = "IMEI")
    @TableField(exist = false)
    private String imei;
}
