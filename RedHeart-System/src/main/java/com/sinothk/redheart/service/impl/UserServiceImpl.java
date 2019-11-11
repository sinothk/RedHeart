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
import com.sinothk.redheart.comm.authorization.TokenCheck;
import com.sinothk.redheart.config.AccountInitLoader;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.domain.LoginRecordEntity;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.UserVo;
import com.sinothk.redheart.repository.LoginReordMapper;
import com.sinothk.redheart.repository.UserMapper;
import com.sinothk.redheart.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Resource(name = "loginReordMapper")
    private LoginReordMapper loginReordMapper;

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
            if (isEmail(email)) {
                userVo.setNickname(email.substring(0, email.indexOf("@")));
            } else {
                userVo.setNickname(String.valueOf(account));
            }

            Date currTime = new Date();
            userVo.setCreateTime(currTime);
            userVo.setLoginTime(currTime);

            userMapper.insert(userVo);

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

    public boolean isEmail(String email) {
        if (email == null || "".equals(email)) return false;
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
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
            tokenParam.put("role", dbOldUser.getRole());
            String token = TokenUtil.createToken(TokenUtil.EXPIRE_TIME_15D, tokenParam);
            dbOldUser.setToken(token);

            // 登录信息设置
            if (userVo.getLoginLat() != null || userVo.getLoginLon() != null) {
                dbOldUser.setLoginLat(userVo.getLoginLat());
                dbOldUser.setLoginLon(userVo.getLoginLon());
                dbOldUser.setImei(userVo.getImei());

                new Thread(() -> {
                    Date date = new Date();
                    // 更新数据库用户信息
                    dbOldUser.setLoginTime(date);
                    userMapper.updateById(dbOldUser);

                    // 保存登录记录
                    LoginRecordEntity lRecordEntity = new LoginRecordEntity();
                    lRecordEntity.setAccount(dbOldUser.getAccount());
                    lRecordEntity.setLoginTime(date);
                    lRecordEntity.setLoginLat(userVo.getLoginLat());
                    lRecordEntity.setLoginLon(userVo.getLoginLon());
                    lRecordEntity.setImei(userVo.getImei());
                    loginReordMapper.insert(lRecordEntity);
                }).start();
            }

            return ResultData.success(dbOldUser);
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
    public ResultData<PageData<List<UserEntity>>> getLastLoginUserPageList(int pageNum, int pageSize) {
        try {
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().orderByDesc(UserEntity::getLoginTime);

            IPage<UserEntity> pageInfo = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

            //
            PageData<List<UserEntity>> pageEntity = new PageData<>();
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
    public ResultData<PageData<List<UserEntity>>> getMaybeLikePageList(int pageNum, int pageSize) {
        try {
            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserEntity::getUserType, "-1").orderByDesc(UserEntity::getLoginTime);

            IPage<UserEntity> pageInfo = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

            //
            PageData<List<UserEntity>> pageEntity = new PageData<>();
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
}
