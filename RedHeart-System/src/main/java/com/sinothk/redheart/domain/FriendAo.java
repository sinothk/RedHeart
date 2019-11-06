package com.sinothk.redheart.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Api(value = "好友关系实体")
@Data
@ToString
public class FriendAo {

    @ApiModelProperty("关注的人数")
    private Integer likeUserNum;

    @ApiModelProperty("粉丝的人数")
    private Integer fansUserNum;

    @ApiModelProperty("关系：0.陌生人；1.他关注我; 2.我关注他； 3. 互相关注 ")
    private Integer relation;

    @ApiModelProperty("用户信息")
    private UserEntity user;

    public FriendAo() {
    }

    public FriendAo(Integer relation) {
        this.relation = relation;
    }
}
