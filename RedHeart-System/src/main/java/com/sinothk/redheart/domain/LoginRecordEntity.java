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

@ApiModel(description = "登录日志信息")
@Data
@ToString
@TableName(value = "tb_comm_login_record")
public class LoginRecordEntity {

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "账号")
    @TableField("account")
    private Long account;

    @ApiModelProperty(value = "登录纬度")
    @TableField("login_lat")
    private Double loginLat;

    @ApiModelProperty(value = "登录经度")
    @TableField("login_lon")
    private Double loginLon;

    @ApiModelProperty(value = "IMEI")
    @TableField("imei")
    private String imei;

    @ApiModelProperty(value = "登录时间")
    @TableField("login_time")
    private Date loginTime;

}
