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

@ApiModel("话题主题实体")
@Data
@ToString
@TableName(value = "tb_app_topic_theme")
public class TopicThemeEntity {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty("主题编码")
    @TableField("theme_code")
    private String themeCode;

    @ApiModelProperty("主题名称")
    @TableField("theme_txt")
    private String themeTxt;

    @ApiModelProperty("主题图标")
    @TableField("theme_icon")
    private String themeIcon;

    @ApiModelProperty("主题备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("排序编号")
    @TableField("sort_num")
    private Integer sortNum;
}
