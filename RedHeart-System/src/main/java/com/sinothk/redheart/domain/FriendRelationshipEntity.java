package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@ApiModel("用户关系(关注/粉丝)实体")


@TableName(value = "tb_comm_friends")
public class FriendRelationshipEntity {

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "喜欢人账号")
    @TableField("liking_account")
    private Long likingAccount;

    @ApiModelProperty(value = "被喜欢人账号")
    @TableField("liked_account")
    private Long likedAccount;

    @ApiModelProperty(value = "开始喜欢时间")
    @TableField("like_time")
    private Date likeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLikingAccount() {
        return likingAccount;
    }

    public void setLikingAccount(Long likingAccount) {
        this.likingAccount = likingAccount;
    }

    public Long getLikedAccount() {
        return likedAccount;
    }

    public void setLikedAccount(Long likedAccount) {
        this.likedAccount = likedAccount;
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}
