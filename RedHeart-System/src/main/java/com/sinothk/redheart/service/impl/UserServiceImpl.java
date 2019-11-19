package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.AccountUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.base.utils.TokenUtil;
import com.sinothk.redheart.config.AccountInitLoader;
import com.sinothk.redheart.domain.FriendRelationshipEntity;
import com.sinothk.redheart.domain.LoginRecordEntity;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.UserVo;
import com.sinothk.redheart.repository.FriendMapper;
import com.sinothk.redheart.repository.LoginReordMapper;
import com.sinothk.redheart.repository.UserMapper;
import com.sinothk.redheart.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Resource(name = "loginReordMapper")
    private LoginReordMapper loginReordMapper;

    @Resource(name = "friendMapper")
    private FriendMapper friendMapper;

    @Override
    public ResultData<UserEntity> addUser(UserEntity userVo) {
        try {
            // 保存数据前核对
            QueryWrapper<UserEntity> wrapperOld = new QueryWrapper<>();
            wrapperOld.lambda().eq(UserEntity::getEmail, userVo.getEmail());
            UserEntity dbOldUser = userMapper.selectOne(wrapperOld);
            if (dbOldUser != null) {
                return ResultData.error("此邮箱已被暂用");
            }
            // 保存数据
            Long account = AccountUtil.create(AccountInitLoader.sysKeepAccountSet);
            userVo.setAccount(account);
            // 设置用户昵称
            String email = userVo.getEmail();
            if (StringUtil.isEmail(email)) {
                userVo.setNickname(email.substring(0, email.indexOf("@")));
            } else {
                userVo.setNickname(String.valueOf(account));
            }

            Date currTime = new Date();
            userVo.setCreateTime(currTime);
            userVo.setLoginTime(currTime);

            userMapper.insert(userVo);

            // 新增默认关注
            try {
                FriendRelationshipEntity frEntity = new FriendRelationshipEntity();
                frEntity.setLikingAccount(account);
                frEntity.setLikedAccount(9999L);
                frEntity.setLikeTime(new Date());
                friendMapper.insert(frEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 返回新数据
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserEntity::getEmail, userVo.getEmail());
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

    @Override
    public ResultData<UserEntity> login(UserVo userVo) {
        try {
            QueryWrapper<UserEntity> wrapperOld = new QueryWrapper<>();
            wrapperOld.lambda().eq(UserEntity::getEmail, userVo.getEmail());
            UserEntity dbOldUser = userMapper.selectOne(wrapperOld);
            if (dbOldUser == null) {
                return ResultData.error("用户不存在");
            }

            if (!userVo.getUserPwd().equals(dbOldUser.getUserPwd())) {
                return ResultData.error("用户密码不正确");
            }

            // 登录成功,组装信息
            // Token部分
            HashMap<String, Object> tokenParam = new HashMap<>();
            tokenParam.put("account", "" + dbOldUser.getAccount());
            tokenParam.put("userName", dbOldUser.getUserName());
            tokenParam.put("sex", "" + dbOldUser.getSex());

//            tokenParam.put("role", dbOldUser.getRole());
            String token = TokenUtil.createToken(TokenUtil.EXPIRE_TIME_15D, tokenParam);
            dbOldUser.setToken(token);

            return ResultData.success(dbOldUser);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<LoginRecordEntity> loginRecord(LoginRecordEntity loginRecordVo) {
        try {
            Date date = new Date();

            loginRecordVo.setLoginTime(date);
            loginReordMapper.insert(loginRecordVo);

            new Thread(() -> {
                UpdateWrapper<UserEntity> wrapper = new UpdateWrapper<>();
                wrapper.lambda().eq(UserEntity::getAccount, loginRecordVo.getAccount());

                UserEntity userEntity = new UserEntity();
                userEntity.setAccount(loginRecordVo.getAccount());
                userEntity.setImei(loginRecordVo.getImei());
                userEntity.setLoginTime(date);

                userEntity.setLoginLat(loginRecordVo.getLoginLat());
                userEntity.setLoginLon(loginRecordVo.getLoginLon());
                userEntity.setLoginProvince(loginRecordVo.getLoginProvince());
                userEntity.setLoginCity(loginRecordVo.getLoginCity());
                userEntity.setLoginDistrict(loginRecordVo.getLoginDistrict());
                userEntity.setLoginAddress(loginRecordVo.getLoginAddress());

                userMapper.update(userEntity, wrapper);
            }).start();

            return ResultData.success(loginRecordVo);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<Boolean> changePwd(String account, String oldPwd, String newPwd) {
        try {
            QueryWrapper<UserEntity> wrapperOld = new QueryWrapper<>();
            wrapperOld.lambda().eq(UserEntity::getAccount, account);
            UserEntity dbOldUser = userMapper.selectOne(wrapperOld);

            if (dbOldUser == null) {
                return ResultData.error("用户不存在");
            }
            if (!oldPwd.equals(dbOldUser.getUserPwd())) {
                return ResultData.error("原密码不正确");
            }

            UserEntity userNewEntity = new UserEntity();
            userNewEntity.setId(dbOldUser.getId());
            userNewEntity.setUserPwd(newPwd);
            userMapper.updateById(userNewEntity);

            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<UserEntity>> getLastLoginUserPageList(int pageNum, int pageSize) {
        try {
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().ne(UserEntity::getUserType, "-2").orderByDesc(UserEntity::getLoginTime);

            IPage<UserEntity> pageInfo = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

            //
            PageData<UserEntity> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(pageNum);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = pageNum * pageSize;
            pageEntity.setHasMore(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<UserEntity>> getMaybeLikePageList(int pageNum, int pageSize) {
        try {
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserEntity::getUserType, "-1").orderByDesc(UserEntity::getLoginTime);

            IPage<UserEntity> pageInfo = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

            //
            PageData<UserEntity> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(pageNum);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = pageNum * pageSize;
            pageEntity.setHasMore(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<List<UserEntity>> findUserByAccountOrUsername(String account, String keyword) {
        try {
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda()
                    .like(UserEntity::getAccount, keyword)
                    .or()
                    .like(UserEntity::getUserName, keyword)
                    .or()
                    .like(UserEntity::getNickname, keyword)
                    .or()
                    .like(UserEntity::getEmail, keyword)
                    .orderByDesc(UserEntity::getLoginTime);

            List<UserEntity> userList = userMapper.selectList(wrapper);

            return ResultData.success(userList);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
