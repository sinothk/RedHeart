package com.sinothk.redheart.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@Api(value = "好友关系实体")


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

    public Integer getLikeUserNum() {
        return likeUserNum;
    }

    public void setLikeUserNum(Integer likeUserNum) {
        this.likeUserNum = likeUserNum;
    }

    public Integer getFansUserNum() {
        return fansUserNum;
    }

    public void setFansUserNum(Integer fansUserNum) {
        this.fansUserNum = fansUserNum;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
