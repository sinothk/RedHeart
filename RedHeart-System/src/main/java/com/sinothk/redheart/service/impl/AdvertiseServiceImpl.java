package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.IdUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.domain.AdvertiseEntity;
import com.sinothk.redheart.repository.AdvertiseMapper;
import com.sinothk.redheart.service.AdvertiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("advertiseService")
public class AdvertiseServiceImpl implements AdvertiseService {

    @Resource(name = "advertiseMapper")
    private AdvertiseMapper advertiseMapper;

    @Override
    public ResultData<Boolean> add(AdvertiseEntity adEntity) {
        try {
            // 处理BizId
            if (StringUtil.isEmpty(adEntity.getAdId())) {
                adEntity.setAdId(IdUtil.generateShortUuid());
            }
            advertiseMapper.insert(adEntity);
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<List<AdvertiseEntity>>> getAdList(int where, int pageNum, int pageSize) {
        try {
            Page<AdvertiseEntity> pageVo = new Page<>(pageNum, pageSize);
            QueryWrapper<AdvertiseEntity> wrapper = new QueryWrapper<>();

            wrapper.lambda().eq(AdvertiseEntity::getWhere, where) // 显示位置
                    .eq(AdvertiseEntity::getStatus, 0); // 查询正常状态

            IPage<AdvertiseEntity> pageInfo = advertiseMapper.selectPage(pageVo, wrapper);

            PageData<List<AdvertiseEntity>> pageEntity = new PageData<>();
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
