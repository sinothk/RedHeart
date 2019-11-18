package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.jpush.pushbyjpush.JPushEntity;
import com.sinothk.jpush.pushbyjpush.JPushHelper;
import com.sinothk.redheart.domain.AppVersionEntity;
import com.sinothk.redheart.repository.AppVersionMapper;
import com.sinothk.redheart.service.AppVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {

    @Resource(name = "appVersionMapper")
    private AppVersionMapper appVersionMapper;

    @Override
    public ResultData<AppVersionEntity> add(AppVersionEntity appEntity) {
        try {
            appVersionMapper.insert(appEntity);

            QueryWrapper<AppVersionEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(AppVersionEntity::getAppCode, appEntity.getAppCode());
            return ResultData.success(appVersionMapper.selectOne(wrapper));
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<AppVersionEntity> getLastApp() {
        try {
            QueryWrapper<AppVersionEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(AppVersionEntity::getAppStatus, 1).orderByDesc(AppVersionEntity::getAppCode);

            List<AppVersionEntity> appVersionList = appVersionMapper.selectList(wrapper);
            if (appVersionList == null || appVersionList.size() == 0) {
                return ResultData.error("没有版本信息");
            }
            return ResultData.success(appVersionList.get(0));
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<AppVersionEntity> changeStatus(AppVersionEntity newAppVersion) {
        try {
            UpdateWrapper<AppVersionEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda().eq(AppVersionEntity::getAppCode, newAppVersion.getAppCode());
            appVersionMapper.update(newAppVersion, updateWrapper);

            QueryWrapper<AppVersionEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(AppVersionEntity::getAppCode, newAppVersion.getAppCode());

            AppVersionEntity lastAppVersion = appVersionMapper.selectOne(wrapper);

            if (lastAppVersion != null && newAppVersion.getAppStatus() == 1) {
//                版本状态：-1:删除；0:提交；1：发布！
                new Thread(() -> {
                    // 通知被关注人
                    JPushHelper.pushMsgForAll(JPushEntity.createData(JPushEntity.MSG_TYPE_VERSION, lastAppVersion));
                }).start();
            }

            return ResultData.success(lastAppVersion);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<AppVersionEntity>> getAppPageList(Integer appStatus, int currPage, int pageSize) {
        try {
            QueryWrapper<AppVersionEntity> wrapper = null;
            if (appStatus != null) {
                wrapper = new QueryWrapper<>();
                wrapper.lambda().eq(AppVersionEntity::getAppStatus, appStatus);
            }

            IPage<AppVersionEntity> pageInfo = appVersionMapper.selectPage(new Page<>(currPage, pageSize), wrapper);

            PageData<AppVersionEntity> pageEntity = new PageData<>();
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

}
