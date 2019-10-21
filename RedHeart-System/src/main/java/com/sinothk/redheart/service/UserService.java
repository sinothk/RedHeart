package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.UserEntity;

public interface UserService {
    ResultData<UserEntity> addUser(UserEntity user);

    ResultData<UserEntity> updateUser(UserEntity user);

    ResultData<UserEntity> login(UserEntity user);
}
