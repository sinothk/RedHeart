package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "版本信息")
@TableName(value = "tb_comm_version")
public class AppVersionEntity {
//    /*表： tb_comm_version*/------------------------
//
//            /*列信息*/-----------
//
//    Field         Type          Collation        Null    Key     Default  Extra           Privileges                       Comment       
//------------  ------------  ---------------  ------  ------  -------  --------------  -------------------------------  --------------
//    id            bigint(11)    (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references
//    app_code      int(11)       (NULL)           YES             (NULL)                   select,insert,update,references  版本号
//    app_name      varchar(32)   utf8_general_ci  YES             (NULL)                   select,insert,update,references  名称
//    app_size      bigint(20)    (NULL)           YES             (NULL)                   select,insert,update,references  大小
//    app_icon      varchar(512)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  app图片
//    app_url       varchar(512)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  下载地址
//    app_describe  varchar(512)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  版本介绍
//    create_time   datetime      (NULL)           YES             (NULL)                   select,insert,update,references  创建时间
//    public_time   datetime      (NULL)           YES             (NULL)                   select,insert,update,references  发布时间
//    nust_update   tinyint(1)    (NULL)           YES             0                        select,insert,update,references  强制升级

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty("版本编号")
    @TableField(value = "app_code")
    private Integer appCode;

    @ApiModelProperty("版本号")
    @TableField(value = "app_name")
    private String appName;

    @ApiModelProperty("文件大小")
    @TableField(value = "app_size")
    private Long appSize;

    @ApiModelProperty("应用图标")
    @TableField(value = "app_icon")
    private String appIcon;

    @ApiModelProperty("应用名称")
    @TableField(value = "file_name")
    private String fileName;

    @ApiModelProperty("应用下载地址")
    @TableField(value = "app_url")
    private String appUrl;

    @ApiModelProperty("版本描述")
    @TableField(value = "app_describe")
    private String appDescribe;

    @ApiModelProperty("版本创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    @ApiModelProperty("版本发布时间")
    @TableField(value = "public_time")
    private Date publicTime;

    @ApiModelProperty("是否强制升级")
    @TableField(value = "must_update")
    private Boolean mustUpdate;

    @ApiModelProperty("发布人账号")
    @TableField(value = "creator_account")
    private Long creatorAccount;

    @ApiModelProperty("版本状态：-1:删除；0:提交；1：发布！")
    @TableField(value = "app_status")
    private Integer appStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAppCode() {
        return appCode;
    }

    public void setAppCode(Integer appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getAppSize() {
        return appSize;
    }

    public void setAppSize(Long appSize) {
        this.appSize = appSize;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppDescribe() {
        return appDescribe;
    }

    public void setAppDescribe(String appDescribe) {
        this.appDescribe = appDescribe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }

    public Boolean getMustUpdate() {
        return mustUpdate;
    }

    public void setMustUpdate(Boolean mustUpdate) {
        this.mustUpdate = mustUpdate;
    }

    public Long getCreatorAccount() {
        return creatorAccount;
    }

    public void setCreatorAccount(Long creatorAccount) {
        this.creatorAccount = creatorAccount;
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }
}
