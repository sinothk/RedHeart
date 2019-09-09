package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@ApiModel(description = "行政单位信息")
@Data
@ToString
@TableName(value = "tb_area")
public class AreaEntity {

//    /*列信息*/-----------
//
//    Field     Type         Collation        Null    Key     Default  Extra           Privileges                       Comment
//--------  -----------  ---------------  ------  ------  -------  --------------  -------------------------------  ---------
//    id        bigint(20)   (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references
//    adcode    varchar(32)  utf8_general_ci  YES             (NULL)                   select,insert,update,references
//    name      varchar(32)  utf8_general_ci  YES             (NULL)                   select,insert,update,references
//    center    varchar(32)  utf8_general_ci  YES             (NULL)                   select,insert,update,references
//    citycode  varchar(32)  utf8_general_ci  YES             (NULL)                   select,insert,update,references
//    level     varchar(32)  utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "")
    @TableField("adcode")
    private String adcode;

    @ApiModelProperty(value = "")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "")
    @TableField("center")
    private String center;

    @ApiModelProperty(value = "")
    @TableField("citycode")
    private String citycode;

    @ApiModelProperty(value = "")
    @TableField("level")
    private String level;

    @ApiModelProperty(value = "")
    @TableField(exist = false)
    private ArrayList<AreaEntity> districts;
}
