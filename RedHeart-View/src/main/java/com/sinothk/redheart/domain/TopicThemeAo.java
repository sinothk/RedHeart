package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("话题返回实体")
@Data
@ToString
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

    @ApiModelProperty("用户账号")
    private Long account;

    @ApiModelProperty("关注时间")
    private Date createTime;
}
