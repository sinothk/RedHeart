package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.AdvertiseEntity;

import java.util.List;

public interface AdvertiseService {

    ResultData<Boolean> add(AdvertiseEntity adEntity);

    ResultData<PageData<AdvertiseEntity>> getAdList(String cityName, int adWhere, int pageNum, int pageSize);

    ResultData<AdvertiseEntity> getWelcomeAdvList(String cityName);
}
