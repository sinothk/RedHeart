package com.sinothk.redheart.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Api(value = "通知返回实体")
@Data
@ToString
public class NoticeVo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("封面")
    private String coverPath;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("发布时间")
    private Date publicTime;

    @ApiModelProperty("发布人账号")
    private Long createAccount;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("生日")
    private Date borthday;

    @ApiModelProperty("阅读量")
    private Integer readerNum;
}
