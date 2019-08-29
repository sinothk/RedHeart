package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.FriendEntity;

import java.util.ArrayList;

public interface FriendService {

    ResultData<PageData<ArrayList<FriendEntity>>> getFriendsList(String username, int pageNum);
}
