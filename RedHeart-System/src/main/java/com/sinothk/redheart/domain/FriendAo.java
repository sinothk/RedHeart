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

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("关注的人数")
    private Integer likeUserNum;

    @ApiModelProperty("粉丝的人数")
    private Integer fansUserNum;

}
