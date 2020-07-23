package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
