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

@ApiModel("主题用户实体")
@Data
@ToString
@TableName(value = "tb_app_topic_theme_user")
public class TopicThemeUserEntity {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @ApiModelProperty("喜欢人账号")
    @TableField("account")
    private Long account;

    @ApiModelProperty("主题编码")
    @TableField("theme_code")
    private String themeCode;

    @ApiModelProperty("排序编号")
    @TableField("create_time")
    private Date createTime;
}
