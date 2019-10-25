package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.AdvertiseEntity;

public interface AdvertiseService {
    ResultData<Boolean> add(AdvertiseEntity adEntity);
}
