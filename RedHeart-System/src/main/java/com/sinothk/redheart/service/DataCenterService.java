package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.DataCenterEntity;
import com.sinothk.redheart.domain.UserLoginAO;

import java.util.List;

public interface DataCenterService {
    ResultData<PageData<UserLoginAO>> getTodayLoginUserPageList(int pageNum, int pageSize);

    ResultData<PageData<UserLoginAO>> getWeekLoginUserPageList(int pageNum, int pageSize);

    ResultData<PageData<UserLoginAO>> getThisMonthLoginUserPageList(int pageNum, int pageSize);

    ResultData<List<DataCenterEntity>> getYearLoginUserPageList(String yearStr);
}
