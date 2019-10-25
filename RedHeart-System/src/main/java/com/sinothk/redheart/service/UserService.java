package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.UserVo;

import java.util.List;

public interface UserService {
    ResultData<UserEntity> addUser(UserEntity user);

    ResultData<UserEntity> updateUser(UserEntity user);

    ResultData<UserEntity> login(UserVo user);

    ResultData<Boolean> changePwd(String account, String oldPwd, String newPwd);

    ResultData<PageData<List<UserEntity>>> getLastLoginUserPageList(int pageNum, int pageSize);
}
