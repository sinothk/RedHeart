package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.jpush.pushbyjpush.JPushEntity;
import com.sinothk.jpush.pushbyjpush.JPushHelper;
import com.sinothk.redheart.domain.FriendAo;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.domain.FriendRelationshipEntity;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.repository.FriendMapper;
import com.sinothk.redheart.repository.UserMapper;
import com.sinothk.redheart.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("friendService")
public class FriendServiceImpl extends ServiceImpl<FriendMapper, FriendRelationshipEntity> implements FriendService {

    @Resource(name = "friendMapper")
    private FriendMapper friendMapper;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public ResultData<PageData<FriendEntity>> getLikeUserList(Long account, int currPage, int pageSize) {
        try {
            Page<FriendEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<FriendEntity> pageInfo = friendMapper.getLikeUserList(pageVo, account);

            PageData<FriendEntity> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(currPage);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = currPage * pageSize;
            pageEntity.setHasMore(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<FriendEntity>> getFensUserList(Long account, int currPage, int pageSize) {
        try {
            Page<FriendEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<FriendEntity> pageInfo = friendMapper.getFensUserList(pageVo, account);

            PageData<FriendEntity> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(currPage);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = currPage * pageSize;
            pageEntity.setHasMore(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<FriendEntity>> getFriendsList(Long account, int currPage, int pageSize) {
        try {
            Page<FriendEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<FriendEntity> pageInfo = friendMapper.getFriendsList(pageVo, account);

            PageData<FriendEntity> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(currPage);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = currPage * pageSize;
            pageEntity.setHasMore(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<FriendAo> addFriend(FriendRelationshipEntity frEntity) {
        try {
            // 判断是否存在
            QueryWrapper<FriendRelationshipEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(FriendRelationshipEntity::getLikingAccount, frEntity.getLikingAccount())
                    .eq(FriendRelationshipEntity::getLikedAccount, frEntity.getLikedAccount());

            FriendRelationshipEntity frDbEntity = friendMapper.selectOne(wrapper);

            if (frDbEntity == null) {
                friendMapper.insert(frEntity);

                new Thread(() -> {
                    // 通知被关注人
                    String alias = String.valueOf(frEntity.getLikedAccount());
                    String data = JPushEntity.createData(JPushEntity.MSG_TYPE_RELATION, frEntity);
                    JPushHelper.pushByAlias(alias, "心跳关注提醒", "你新增加了一位爱慕人 ... ", data);
                }).start();

                return ResultData.success(new FriendAo(2));
            } else {

                if (frEntity.getLikedAccount() == 9999) {
                    return ResultData.error("不能取消关注");
                }

                friendMapper.deleteById(frDbEntity.getId());
                return ResultData.success(new FriendAo(0));
            }
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<FriendEntity> getUserInfo(String loginAccount, String targetAccount) {
        try {
            // 获取用户信息
            FriendEntity frInfoEntity = friendMapper.getUserInfo(loginAccount, targetAccount);
            return ResultData.success(frInfoEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<FriendAo> getRelationUser(String loginAccount, String targetAccount) {
        try {
            FriendAo friendAo = new FriendAo();
            // 获取用户信息
            QueryWrapper<UserEntity> userWrapper = new QueryWrapper<>();
            userWrapper.lambda().eq(UserEntity::getAccount, targetAccount);
            UserEntity userEntity = userMapper.selectOne(userWrapper);
            friendAo.setUser(userEntity);

            // 获得目标用户喜欢的人数
            QueryWrapper<FriendRelationshipEntity> likeWrapper = new QueryWrapper<>();
            likeWrapper.lambda().eq(FriendRelationshipEntity::getLikingAccount, targetAccount);
            List<FriendRelationshipEntity> likeList = friendMapper.selectList(likeWrapper);
            friendAo.setLikeUserNum(likeList.size());

            // 获得喜欢目标用户的人数
            QueryWrapper<FriendRelationshipEntity> fansWrapper = new QueryWrapper<>();
            fansWrapper.lambda().eq(FriendRelationshipEntity::getLikedAccount, targetAccount);
            List<FriendRelationshipEntity> fansList = friendMapper.selectList(fansWrapper);
            friendAo.setFansUserNum(fansList.size());

            Integer ILikeHer = friendMapper.findUserLike(loginAccount, targetAccount);
            Integer HerLikeNum = friendMapper.findUserLike(targetAccount, loginAccount);

            if (ILikeHer > 0 && HerLikeNum > 0) {
                friendAo.setRelation(3);
            } else if (ILikeHer > 0 && HerLikeNum == 0) {
                friendAo.setRelation(2);
            } else if (ILikeHer == 0 && HerLikeNum > 0) {
                friendAo.setRelation(1);
            } else {
                friendAo.setRelation(0);
            }

            return ResultData.success(friendAo);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}