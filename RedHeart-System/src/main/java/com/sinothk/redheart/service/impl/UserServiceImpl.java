package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.AccountUtil;
import com.sinothk.redheart.config.AccountInitLoader;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.repository.UserMapper;
import com.sinothk.redheart.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public ResultData<UserEntity> addUser(UserEntity user) {
        try {
            // 保存数据前核对
            QueryWrapper<UserEntity> wrapperOld = new QueryWrapper<>();
            wrapperOld.lambda().eq(UserEntity::getEmail, user.getEmail());
            UserEntity dbOldUser = userMapper.selectOne(wrapperOld);
            if (dbOldUser != null) {
                return ResultData.error("此邮箱已被暂用");
            }

            // 保存数据
            Long account = AccountUtil.create(AccountInitLoader.sysKeepAccountSet);
            user.setAccount(account);
            userMapper.insert(user);

            // 返回新数据
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserEntity::getEmail, user.getEmail());
            UserEntity dbUser = userMapper.selectOne(wrapper);

            if (dbUser != null) {
                return ResultData.success(dbUser);
            } else {
                return ResultData.error("暂无数据");
            }
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<UserEntity> updateUser(UserEntity user) {

        try {
            // 保存数据前核对
            QueryWrapper<UserEntity> wrapperOld = new QueryWrapper<>();
            wrapperOld.lambda().eq(UserEntity::getAccount, user.getAccount());
            UserEntity dbOldUser = userMapper.selectOne(wrapperOld);
            if (dbOldUser == null) {
                return ResultData.error("用户不存在，请核对Account是否正确");
            }

            // 更新数据
            UpdateWrapper<UserEntity> uWrapperOld = new UpdateWrapper<>();
            uWrapperOld.lambda().eq(UserEntity::getAccount, user.getAccount());
            userMapper.update(user, uWrapperOld);

            // 返回新数据
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserEntity::getAccount, user.getAccount());
            UserEntity dbUser = userMapper.selectOne(wrapper);

            if (dbUser != null) {
                return ResultData.success(dbUser);
            } else {
                return ResultData.error("暂无数据");
            }
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
