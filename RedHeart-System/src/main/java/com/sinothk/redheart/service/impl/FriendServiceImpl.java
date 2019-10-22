package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.repository.FriendMapper;
import com.sinothk.redheart.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("friendService")
public class FriendServiceImpl implements FriendService {


    @Resource(name = "friendMapper")
    private FriendMapper friendMapper;

    @Override
    public ResultData<PageData<List<FriendEntity>>> getLikeUserList(Long account, int currPage, int pageSize) {
        try {
            Page<FriendEntity> pageVo = new Page<>(currPage, pageSize);
            IPage pageInfo = friendMapper.getLikeUserList(pageVo, account);
            PageData<List<FriendEntity>> pageEntity = new PageData<>();
            pageEntity.setData(pageInfo.getRecords());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<List<FriendEntity>>> getFensUserList(Long account, int currPage, int pageSize) {
        try {
            Page<FriendEntity> pageVo = new Page<>(currPage, pageSize);
            IPage pageInfo = friendMapper.getFensUserList(pageVo, account);
            PageData<List<FriendEntity>> pageEntity = new PageData<>();
            pageEntity.setData(pageInfo.getRecords());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}