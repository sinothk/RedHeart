package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.domain.FriendRelationshipEntity;
import com.sinothk.redheart.repository.FriendMapper;
import com.sinothk.redheart.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("friendService")
public class FriendServiceImpl extends ServiceImpl<FriendMapper, FriendRelationshipEntity> implements FriendService {


    @Resource(name = "friendMapper")
    private FriendMapper friendMapper;

    @Override
    public ResultData<PageData<List<FriendEntity>>> getLikeUserList(Long account, int currPage, int pageSize) {
        try {
            Page<FriendEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<FriendEntity> pageInfo = friendMapper.getLikeUserList(pageVo, account);

            PageData<List<FriendEntity>> pageEntity = new PageData<>();
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
    public ResultData<PageData<List<FriendEntity>>> getFensUserList(Long account, int currPage, int pageSize) {
        try {
            Page<FriendEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<FriendEntity> pageInfo = friendMapper.getFensUserList(pageVo, account);

            PageData<List<FriendEntity>> pageEntity = new PageData<>();
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
    public ResultData<PageData<List<FriendEntity>>> getFriendsList(Long account, int currPage, int pageSize) {
        try {
            Page<FriendEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<FriendEntity> pageInfo = friendMapper.getFriendsList(pageVo, account);

            PageData<List<FriendEntity>> pageEntity = new PageData<>();
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
    public ResultData<String> addFriend(FriendRelationshipEntity frEntity) {
        try {
            // 判断是否存在
            QueryWrapper<FriendRelationshipEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(FriendRelationshipEntity::getLikingAccount, frEntity.getLikingAccount())
                    .eq(FriendRelationshipEntity::getLikedAccount, frEntity.getLikedAccount());

            FriendRelationshipEntity frDbEntity = friendMapper.selectOne(wrapper);
            if (frDbEntity == null) {
                friendMapper.insert(frEntity);
                return ResultData.success("关注成功");
            } else {
                friendMapper.deleteById(frDbEntity.getId());
                return ResultData.success("取消关注");
            }
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}