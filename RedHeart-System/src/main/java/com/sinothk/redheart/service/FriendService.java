package com.sinothk.redheart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.FriendAo;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.domain.FriendRelationshipEntity;
import com.sinothk.redheart.domain.UserEntity;

import java.util.ArrayList;
import java.util.List;

public interface FriendService extends IService<FriendRelationshipEntity> {

    ResultData<PageData<FriendEntity>> getLikeUserList(Long account, int currPage, int pageSize);

    ResultData<PageData<FriendEntity>> getFensUserList(Long account, int currPage, int pageSize);

    ResultData<PageData<FriendEntity>> getFriendsList(Long account, int currPage, int pageSize);

    ResultData<FriendAo> addFriend(FriendRelationshipEntity frEntity);

    ResultData<FriendEntity> getUserInfo(String loginAccount, String targetAccount);

    ResultData<FriendAo> getRelationUser(String loginAccount, String targetAccount);
}
