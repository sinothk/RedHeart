package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class TopicAo extends TopicEntity{

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("话题Id")
    private String topicId;

    @ApiModelProperty("发布人账号")
    private Long account;

    @ApiModelProperty("话题主题")
    private String topicTheme;

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
}
