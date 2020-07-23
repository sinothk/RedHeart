package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@ApiModel("话题主题实体")


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
}
