package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.domain.FriendRelationshipEntity;

import java.util.ArrayList;
import java.util.List;

public interface FriendService {

    ResultData<PageData<List<FriendEntity>>> getLikeUserList(Long account, int currPage, int pageSize);

    ResultData<PageData<List<FriendEntity>>> getFensUserList(Long account, int currPage, int pageSize);
}
