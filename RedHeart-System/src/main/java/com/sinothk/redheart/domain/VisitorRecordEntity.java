package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@ApiModel(description = "用户访问记录表")


@TableName(value = "tb_comm_visitor")
public class VisitorRecordEntity {
//    /*表： tb_comm_visitor*/------------------------
//
//            /*列信息*/-----------
//
//    Field       Type         Collation        Null    Key     Default  Extra           Privileges                       Comment
//----------  -----------  ---------------  ------  ------  -------  --------------  -------------------------------  --------------------------------------
//    id          bigint(32)   (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references
//    account     varchar(64)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  被访问者
//    visitor     varchar(64)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  访问者
//    visit_time  datetime     (NULL)           YES             (NULL)                   select,insert,update,references  访问时间
//    visit_type  tinyint(1)   (NULL)           YES             0                        select,insert,update,references  访问类型：0：主页；1.动态
//    visit_num   bigint(20)   (NULL)           YES             0                        select,insert,update,references  访问次数

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "被访问者账号")
    @TableField("account")
    private String account;

    @ApiModelProperty(value = "访问者账号")
    @TableField("visitor")
    private String visitor;

    @ApiModelProperty(value = "访问时间")
    @TableField("visit_time")
    private Date visitTime;

    @ApiModelProperty(value = "访问类型：0：主页；1.动态")
    @TableField("visit_type")
    private Integer visitType;

    @ApiModelProperty(value = "访问次数")
    @TableField("visit_num")
    private Integer visitNum;

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
}
