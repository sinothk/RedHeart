package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class TopicAo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("话题Id")
    private String topicId;

    @ApiModelProperty("发布人账号")
    private Long account;

    @ApiModelProperty("话题主题")
    private String topicTheme;

    @ApiModelProperty("话题主题Code")
    private String themeCode;

    @ApiModelProperty("话题封面")
    private String themeIcon;

    @ApiModelProperty("话题内容")
    private String topicTitle;

    @ApiModelProperty("话题内容")
    private String topicContent;

    @ApiModelProperty("发布纬度")
    private Double locLat;

    @ApiModelProperty("发布经度")
    private Double locLng;

    @ApiModelProperty("发布地址")
    private String locAddress;

    @ApiModelProperty("发布时间")
    private Date createTime;

    @ApiModelProperty("发布时间")
    private Date updateTime;

    @ApiModelProperty("话题图片")
    private String topicImg;

    @ApiModelProperty("话题封面图片")
    private String topicCover;

    @ApiModelProperty("点赞数")
    private Integer praiseNum;

    @ApiModelProperty("点赞数")
    private Integer commentNum;

    // ======================================

    @ApiModelProperty("发布人姓名")
    private String userName;

    @ApiModelProperty("发布人头像")
    private String userAvatar;

    @ApiModelProperty("发布人昵称")
    private String nickname;

    @ApiModelProperty("发布人性别")
    private String sex;

    @ApiModelProperty("发布人生日")
    private Date userBorthday;

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

    public String getThemeCode() {
        return themeCode;
    }

    public void setThemeCode(String themeCode) {
        this.themeCode = themeCode;
    }

    public String getThemeIcon() {
        return themeIcon;
    }

    public void setThemeIcon(String themeIcon) {
        this.themeIcon = themeIcon;
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

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getUserBorthday() {
        return userBorthday;
    }

    public void setUserBorthday(Date userBorthday) {
        this.userBorthday = userBorthday;
    }
}
