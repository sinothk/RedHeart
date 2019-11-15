package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.AppVersionEntity;

public interface AppVersionService {
    ResultData<AppVersionEntity> add(AppVersionEntity appEntity);

    ResultData<AppVersionEntity> getLastApp();

    ResultData<AppVersionEntity> changeStatus(AppVersionEntity newAppVersion);

    ResultData<PageData<AppVersionEntity>> getAppPageList(Integer appStatus, int pageNum, int pageSize);
}
