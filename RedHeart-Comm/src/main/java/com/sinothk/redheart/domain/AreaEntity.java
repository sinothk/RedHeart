package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("区域实体")
@TableName(value = "tb_area")
public class AreaEntity {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父编码")
    @TableField(value = "parent")
    private String parent;

    @ApiModelProperty(value = "地址编码")
    @TableField(value = "adcode")
    private String adCode;

    @ApiModelProperty(value = "地名")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "行政中心经纬度")
    @TableField(value = "center")
    private String center;

    @ApiModelProperty(value = "所属城市编码")
    @TableField(value = "citycode")
    private String cityCode;

    @ApiModelProperty(value = "当前级别")
    @TableField(value = "level")
    private String level;
}
