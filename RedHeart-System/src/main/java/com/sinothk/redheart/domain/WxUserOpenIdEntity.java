package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel(description = "用户信息")


@TableName(value = "tb_comm_user_openid")
public class WxUserOpenIdEntity {
//    Field      Type         Collation        Null    Key     Default  Extra           Privileges                       Comment
//---------  -----------  ---------------  ------  ------  -------  --------------  -------------------------------  ---------------------
//    id         int(11)      (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references
//    u_account  varchar(64)  utf8_general_ci  YES     UNI     (NULL)                   select,insert,update,references  系统用户account
//    open_id    varchar(64)  utf8_general_ci  YES     UNI     (NULL)                   select,insert,update,references  微信OpenId


    public WxUserOpenIdEntity() {
    }

    public WxUserOpenIdEntity(String openId, String account) {
        this.openId = openId;
        this.account = account;
    }

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "账号")
    @TableField("u_account")
    private String account;

    @ApiModelProperty(value = "微信OpenId")
    @TableField("open_id")
    private String openId;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
