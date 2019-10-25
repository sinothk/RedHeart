package com.sinothk.redheart.service.impl;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.AdvertiseEntity;
import com.sinothk.redheart.repository.AdvertiseMapper;
import com.sinothk.redheart.service.AdvertiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("advertiseService")
public class AdvertiseServiceImpl implements AdvertiseService {

    @Resource(name = "advertiseMapper")
    private AdvertiseMapper advertiseMapper;

    @Override
    public ResultData<Boolean> add(AdvertiseEntity adEntity) {
        try {
            advertiseMapper.insert(adEntity);
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
