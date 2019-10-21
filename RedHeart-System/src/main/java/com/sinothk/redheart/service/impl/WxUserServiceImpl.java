package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinothk.base.utils.AccountUtil;
import com.sinothk.redheart.config.AccountInitLoader;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.WxUserEntity;
import com.sinothk.redheart.domain.WxUserOpenIdEntity;
import com.sinothk.redheart.domain.WxUserVo;
import com.sinothk.redheart.repository.UserMapper;
import com.sinothk.redheart.repository.WxUserOpenIdMapper;
import com.sinothk.redheart.service.WxUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "wxUserOpenIdMapper")
    private WxUserOpenIdMapper wxUserOpenIdMapper;

    @Override
    public WxUserEntity saveOrFindUser(WxUserVo wxApiEntity) {
        QueryWrapper<WxUserOpenIdEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(WxUserOpenIdEntity::getOpenId, wxApiEntity.getOpenid());

        // 查询微信用户是否存在
        WxUserOpenIdEntity wxUserOpenIdEntity = wxUserOpenIdMapper.selectOne(wrapper);
        if (wxUserOpenIdEntity == null) {
            // 不存在当前用户，则注册
            Long account = AccountUtil.create(AccountInitLoader.sysKeepAccountSet); //AccountInitLoader.getInitAccountSet()

            // 保存微信账号信息
            WxUserOpenIdEntity wxUserOpenIdNewEntity = new WxUserOpenIdEntity(wxApiEntity.getOpenid(), "" + account);
            wxUserOpenIdMapper.insert(wxUserOpenIdNewEntity);

            // 保存注册用户
            UserEntity userEntity = new UserEntity();
            userEntity.setAccount(account);
            userEntity.setUserName("" + account);
            userEntity.setAvatar(wxApiEntity.getAvatarUrl());
            userEntity.setSex(wxApiEntity.getGender());
            userEntity.setNickname(wxApiEntity.getNickName());
            userEntity.setUserPwd("123456");

            userMapper.insert(userEntity);

            // 构建用户信息
            QueryWrapper<UserEntity> userNewWrapper = new QueryWrapper<>();
            userNewWrapper.lambda().eq(UserEntity::getAccount, account);
            UserEntity userNewEntity = userMapper.selectOne(userNewWrapper);

            // 查出新数据赋值
            WxUserEntity wxUserEntity = new WxUserEntity();
            BeanUtils.copyProperties(userNewEntity, wxUserEntity);
            wxUserEntity.setOpenId(wxApiEntity.getOpenid());
            return wxUserEntity;
        } else {
            try { // 用户已存在
                QueryWrapper<UserEntity> userWrapper = new QueryWrapper<>();
                userWrapper.lambda().eq(UserEntity::getAccount, wxUserOpenIdEntity.getAccount());
                // 找到用户信息并赋值
                UserEntity userEntity = userMapper.selectOne(userWrapper);
                WxUserEntity wxUserEntity = new WxUserEntity();
                BeanUtils.copyProperties(userEntity, wxUserEntity);
                wxUserEntity.setOpenId(wxUserOpenIdEntity.getOpenId());
                return wxUserEntity;
            } catch (Exception e) {
                return null;
            }
        }
    }
}
