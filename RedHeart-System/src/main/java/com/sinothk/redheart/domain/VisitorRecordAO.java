package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@ApiModel(description = "用户访问记录表")


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Integer getVisitType() {
        return visitType;
    }

    public void setVisitType(Integer visitType) {
        this.visitType = visitType;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
