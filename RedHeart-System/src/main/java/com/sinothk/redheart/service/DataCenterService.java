package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.UserLoginAOEntity;

import java.util.List;

public interface DataCenterService {
    ResultData<PageData<List<UserLoginAOEntity>>> getTodayLoginUserPageList(int pageNum, int pageSize);
}
