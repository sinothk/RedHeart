package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("话题实体")
@Data
@ToString
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
}
