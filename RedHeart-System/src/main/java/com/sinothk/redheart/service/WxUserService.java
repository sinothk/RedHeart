package com.sinothk.redheart.service;

import com.sinothk.redheart.domain.WxUserEntity;
import com.sinothk.redheart.domain.WxUserVo;

public interface WxUserService {

    WxUserEntity saveOrFindUser(WxUserVo wxApiEntity);

}
