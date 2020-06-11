package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class VisitorRecordVO {

    @ApiModelProperty(value = "被访问者账号")
    private String account;

    @ApiModelProperty(value = "访问者账号")
    private String visitor;

    @ApiModelProperty(value = "访问类型：0：主页；1.动态")
    private Integer visitType;
}
