package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.UserEntity;

public interface UserService {
    ResultData<UserEntity> addUser(UserEntity user);
}
