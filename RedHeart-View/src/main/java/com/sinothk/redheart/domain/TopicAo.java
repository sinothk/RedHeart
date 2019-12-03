package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
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
}
