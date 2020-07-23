package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@ApiModel("话题返回实体")


public class TopicThemeAo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("主题编码")
    private String themeCode;

    @ApiModelProperty("主题名称")
    private String themeTxt;

    @ApiModelProperty("主题图标")
    private String themeIcon;

    @ApiModelProperty("主题备注")
    private String remark;

    @ApiModelProperty("排序编号")
    private Integer sortNum;

    @ApiModelProperty("主题下的话题总数")
    private Integer topicNum;

    // ========================================
    @ApiModelProperty("用户账号")
    private Long account;

    @ApiModelProperty("关注时间")
    private Date createTime;

    @ApiModelProperty("当前用户是否关注：0：否；1：关注")
    private Integer relNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThemeCode() {
        return themeCode;
    }

    public void setThemeCode(String themeCode) {
        this.themeCode = themeCode;
    }

    public String getThemeTxt() {
        return themeTxt;
    }

    public void setThemeTxt(String themeTxt) {
        this.themeTxt = themeTxt;
    }

    public String getThemeIcon() {
        return themeIcon;
    }

    public void setThemeIcon(String themeIcon) {
        this.themeIcon = themeIcon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(Integer topicNum) {
        this.topicNum = topicNum;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRelNum() {
        return relNum;
    }

    public void setRelNum(Integer relNum) {
        this.relNum = relNum;
    }
}
