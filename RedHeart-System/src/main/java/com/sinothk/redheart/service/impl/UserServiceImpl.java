package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

            // 查询返回新增数据
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserEntity::getEmail, user.getEmail());
            UserEntity dbUser = userMapper.selectOne(wrapper);

            if (dbUser != null) {
                return ResultData.success(dbUser);
            } else {
                return ResultData.error("暂无数据");
            }
        } catch (Exception e) {
            return ResultData.error(e.getMessage());
        }
    }
}
