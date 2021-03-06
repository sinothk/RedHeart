package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.LoginRecordEntity;
import com.sinothk.redheart.domain.UserAO;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.UserVo;

import java.util.List;

public interface UserService {

    ResultData<UserEntity> addUser(UserEntity user);

    ResultData<UserEntity> updateUser(UserEntity user);

    ResultData<UserEntity> login(UserVo user);

    ResultData<Boolean> changePwd(String account, String oldPwd, String newPwd);

    ResultData<PageData<UserEntity>> getLastLoginUserPageList(String currAccount, int pageNum, int pageSize);

    ResultData<PageData<UserEntity>> getMaybeLikePageList(String likingAccount, int pageNum, int pageSize);

    ResultData<List<UserEntity>> findUserByAccountOrUsername(String account, String keyword);

    ResultData<LoginRecordEntity> loginRecord(LoginRecordEntity loginRecordVo);

    ResultData<PageData<UserAO>> getNearbyUserPageList(int sex, Double centerLat, Double centerLng, int pageNum, int pageSize);

    ResultData<PageData<UserEntity>> getNewUserPageList(String currAccount, int pageNum, int pageSize);

    ResultData<PageData<UserEntity>> getUsersByTopsTopicPageList(String sex, int pageNum, int pageSize);
}
