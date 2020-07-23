package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;



import java.util.Date;



public class VisitorRecordVO {

    @ApiModelProperty(value = "被访问者账号")
    private String account;

    @ApiModelProperty(value = "访问者账号")
    private String visitor;

    @ApiModelProperty(value = "访问类型：0：主页；1.动态")
    private Integer visitType;

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

    public Integer getVisitType() {
        return visitType;
    }

    public void setVisitType(Integer visitType) {
        this.visitType = visitType;
    }
}
