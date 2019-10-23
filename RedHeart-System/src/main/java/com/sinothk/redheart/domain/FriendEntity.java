package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel(description = "好友信息")
@Data
@ToString
public class FriendEntity {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "账号")
    private Long account;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "电话号码")
    private String phoneNum;

    @ApiModelProperty(value = "用户状态")
    private Integer userStatus;

    @ApiModelProperty(value = "用户生日")
    private Date userBorthday;

    @ApiModelProperty(value = "关注或被关注时间")
    private Date likeTime;

    @ApiModelProperty(value = "是否喜欢")
    private Integer likingNum;

    @ApiModelProperty(value = "是否被喜欢")
    private Integer likedNum;
}
