package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@ApiModel(description = "登录日志信息")


@TableName(value = "tb_comm_login_record")
public class LoginRecordEntity {

//    /*表： tb_comm_login_record*/-----------------------------
//
//            /*列信息*/-----------
//
//    Field           Type          Collation        Null    Key     Default  Extra           Privileges                       Comment
//--------------  ------------  ---------------  ------  ------  -------  --------------  -------------------------------  -------------------------
//    id              int(11)       (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  key
//    account         bigint(20)    (NULL)           NO              (NULL)                   select,insert,update,references  登录人账号
//    imei            varchar(32)   utf8_general_ci  YES             (NULL)                   select,insert,update,references  设备识别号
//    login_time      datetime      (NULL)           NO              (NULL)                   select,insert,update,references  登录时间
//    login_lat       double        (NULL)           YES             (NULL)                   select,insert,update,references  登录纬度
//    login_lon       double        (NULL)           YES             (NULL)                   select,insert,update,references  登录经度
//    login_address   varchar(128)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  登录位置
//    login_province  varchar(16)   utf8_general_ci  YES             (NULL)                   select,insert,update,references  省/直辖市/自治区
//    login_city      varchar(16)   utf8_general_ci  YES             (NULL)                   select,insert,update,references  市
//    login_district  varchar(16)   utf8_general_ci  YES             (NULL)                   select,insert,update,references  区县

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "账号")
    @TableField("account")
    private Long account;

    @ApiModelProperty(value = "IMEI")
    @TableField("imei")
    private String imei;

    @ApiModelProperty(value = "登录时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty(value = "登录纬度")
    @TableField("login_lat")
    private Double loginLat;

    @ApiModelProperty(value = "登录经度")
    @TableField("login_lon")
    private Double loginLon;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Double getLoginLat() {
        return loginLat;
    }

    public void setLoginLat(Double loginLat) {
        this.loginLat = loginLat;
    }

    public Double getLoginLon() {
        return loginLon;
    }

    public void setLoginLon(Double loginLon) {
        this.loginLon = loginLon;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getLoginProvince() {
        return loginProvince;
    }

    public void setLoginProvince(String loginProvince) {
        this.loginProvince = loginProvince;
    }

    public String getLoginCity() {
        return loginCity;
    }

    public void setLoginCity(String loginCity) {
        this.loginCity = loginCity;
    }

    public String getLoginDistrict() {
        return loginDistrict;
    }

    public void setLoginDistrict(String loginDistrict) {
        this.loginDistrict = loginDistrict;
    }
}