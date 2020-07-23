package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

public class WxUserEntity extends UserEntity{

    @ApiModelProperty(value = "微信OpenId")
    @TableField(exist = false)
    private String openId;

    @ApiModelProperty(value = "微信sessionKey")
    @TableField(exist = false)
    private String session_key;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
}
