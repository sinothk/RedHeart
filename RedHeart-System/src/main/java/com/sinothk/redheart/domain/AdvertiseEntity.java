package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@Api(value = "广告实体")
@TableName("tb_comm_advertise")
public class AdvertiseEntity {

//    CREATE TABLE `tb_comm_advertise` (
//            `id` bigint(20) NOT NULL AUTO_INCREMENT,
//  `ad_title` varchar(64) DEFAULT NULL,
//  `ad_img` varchar(512) DEFAULT NULL,
//  `ad_url` varchar(512) DEFAULT NULL,
//  `ad_seat` int(2) DEFAULT NULL COMMENT '显示位置',
//            `ad_status` int(1) DEFAULT NULL COMMENT '状态',
//            `create_time` datetime DEFAULT NULL COMMENT '新建时间',
//            `start_time` datetime DEFAULT NULL COMMENT '显示时间',
//            `end_time` datetime DEFAULT NULL COMMENT '到期时间', create_account
//    PRIMARY KEY (`id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8


    public AdvertiseEntity() {
    }

    public AdvertiseEntity(String adImg) {
        this.adImg = adImg;
    }

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty("创建人账号")
    @TableField(value = "create_account")
    private Long createAccount;

    @ApiModelProperty("广告ID")
    @TableField(value = "ad_id")
    private String adId;

    @ApiModelProperty("标题")
    @TableField(value = "ad_title")
    private String adTitle;

    @ApiModelProperty("封面图片地址")
    @TableField(value = "ad_img")
    private String adImg;

    @ApiModelProperty("广告详情")
    @TableField(value = "ad_url")
    private String adUrl;

    @ApiModelProperty("显示位置")
    @TableField(value = "ad_where")
    private Integer adWhere;

    @ApiModelProperty("状态")
    @TableField(value = "ad_status")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    @ApiModelProperty("显示时间")
    @TableField(value = "start_time")
    private Date startTime;

    @ApiModelProperty("到期时间")
    @TableField(value = "end_time")
    private Date endTime;

    @ApiModelProperty("显示城市")
    @TableField(value = "ad_city")
    private String cityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Long createAccount) {
        this.createAccount = createAccount;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdImg() {
        return adImg;
    }

    public void setAdImg(String adImg) {
        this.adImg = adImg;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public Integer getAdWhere() {
        return adWhere;
    }

    public void setAdWhere(Integer adWhere) {
        this.adWhere = adWhere;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
