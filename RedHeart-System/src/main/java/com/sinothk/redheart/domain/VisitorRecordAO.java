package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel(description = "用户访问记录表")
@Data
@ToString
public class VisitorRecordAO {

//    SELECT
//    tcv.id id,
//    tcv.account account,
//    tcv.visitor visitor,
//    tcv.visit_time visitTime,
//    tcv.visit_type visitType,
//    tcv.visit_num visitNum,
//
//    tcu.`u_nickname` nickname,
//    tcu.`user_name` userName,
//    tcu.`u_avatar` userAvatar
//            FROM
//    tb_comm_visitor tcv,
//    tb_comm_user tcu
//
//    WHERE
//    tcv.`account` = "10118" AND tcv.visitor = tcu.`u_account`

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "被访问者账号")
    private String account;

    @ApiModelProperty(value = "访问者账号")
    private String visitor;

    @ApiModelProperty(value = "访问时间")
    private Date visitTime;

    @ApiModelProperty(value = "访问类型：0：主页；1.动态")
    private Integer visitType;

    @ApiModelProperty(value = "访问次数")
    private Integer visitNum;

    // ================================================
    @ApiModelProperty(value = "访问人昵称")
    private String nickname;

    @ApiModelProperty(value = "访问人userName")
    private String userName;

    @ApiModelProperty(value = "访问人头像")
    private String userAvatar;
}
