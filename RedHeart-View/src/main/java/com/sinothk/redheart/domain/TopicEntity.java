package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;



import java.util.Date;

@ApiModel("话题实体")


@TableName(value = "tb_app_topic")
public class TopicEntity {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty("话题Id")
    @TableField("topic_id")
    private String topicId;

    @ApiModelProperty("发布人账号")
    @TableField("account")
    private Long account;

    @ApiModelProperty("话题主题")
    @TableField("topic_theme")
    private String topicTheme;

    @ApiModelProperty("话题标题")
    @TableField("topic_title")
    private String topicTitle;

    @ApiModelProperty("话题内容")
    @TableField("topic_content")
    private String topicContent;

    @ApiModelProperty("发布纬度")
    @TableField("loc_lat")
    private Double locLat;

    @ApiModelProperty("发布经度")
    @TableField("loc_lng")
    private Double locLng;

    @ApiModelProperty("发布地址")
    @TableField("loc_address")
    private String locAddress;

    @ApiModelProperty("发布时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("发布时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty("话题图片")
    @TableField("topic_img")
    private String topicImg;

    @ApiModelProperty("话题封面图片")
    @TableField("topic_cover")
    private String topicCover;

    @ApiModelProperty("话题性别分类")
    @TableField("sex_type")
    private Integer sexType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getTopicTheme() {
        return topicTheme;
    }

    public void setTopicTheme(String topicTheme) {
        this.topicTheme = topicTheme;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public Double getLocLat() {
        return locLat;
    }

    public void setLocLat(Double locLat) {
        this.locLat = locLat;
    }

    public Double getLocLng() {
        return locLng;
    }

    public void setLocLng(Double locLng) {
        this.locLng = locLng;
    }

    public String getLocAddress() {
        return locAddress;
    }

    public void setLocAddress(String locAddress) {
        this.locAddress = locAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getTopicCover() {
        return topicCover;
    }

    public void setTopicCover(String topicCover) {
        this.topicCover = topicCover;
    }

    public Integer getSexType() {
        return sexType;
    }

    public void setSexType(Integer sexType) {
        this.sexType = sexType;
    }
}
