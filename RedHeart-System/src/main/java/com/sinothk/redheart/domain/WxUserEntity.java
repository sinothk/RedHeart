package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class WxUserEntity extends UserEntity{

    @ApiModelProperty(value = "微信OpenId")
    @TableField(exist = false)
    private String openId;

    @ApiModelProperty(value = "微信sessionKey")
    @TableField(exist = false)
    private String session_key;
}
