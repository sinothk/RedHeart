package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.UserLoginAOEntity;
import com.sinothk.redheart.repository.DataCenterMapper;
import com.sinothk.redheart.service.DataCenterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dataCenterService")
public class DataCenterServiceImpl implements DataCenterService {

    @Resource(name = "dataCenterMapper")
    private DataCenterMapper dataCenterMapper;

    @Override
    public ResultData<PageData<List<UserLoginAOEntity>>> getTodayLoginUserPageList(int currPage, int pageSize) {
        try {
            Page<UserEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<UserLoginAOEntity> pageInfo = dataCenterMapper.getTodayLoginUserPageList(pageVo);

            PageData<List<UserLoginAOEntity>> pageEntity = new PageData<>();
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
