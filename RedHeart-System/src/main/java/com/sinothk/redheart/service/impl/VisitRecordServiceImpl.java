package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.VisitorRecordAO;
import com.sinothk.redheart.domain.VisitorRecordEntity;
import com.sinothk.redheart.repository.VisitRecordMapper;
import com.sinothk.redheart.service.VisitRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("visitRecordService")
public class VisitRecordServiceImpl implements VisitRecordService {

    @Resource(name = "visitRecordMapper")
    private VisitRecordMapper noticeReaderMapper;

    @Override
    public ResultData<Boolean> add(VisitorRecordEntity entity) {
        try {
            QueryWrapper<VisitorRecordEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                    .eq(VisitorRecordEntity::getAccount, entity.getAccount())
                    .eq(VisitorRecordEntity::getVisitor, entity.getVisitor())
                    .eq(VisitorRecordEntity::getVisitType, entity.getVisitType());

            VisitorRecordEntity dbEntity = noticeReaderMapper.selectOne(queryWrapper);

            if (dbEntity == null) {
                entity.setVisitNum(1);
                entity.setVisitTime(new Date());
                noticeReaderMapper.insert(entity);
            } else {
                if (dbEntity.getVisitNum() == null) {
                    dbEntity.setVisitNum(1);
                }
                dbEntity.setVisitNum(dbEntity.getVisitNum() + 1);
                dbEntity.setVisitTime(new Date());
                noticeReaderMapper.updateById(dbEntity);
            }
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<VisitorRecordAO>> getVisitRecordList(String account, int pageNum, int pageSize) {
        try {
            Page<VisitorRecordAO> pageVo = new Page<>(pageNum, pageSize);
            IPage<VisitorRecordAO> pageInfo = noticeReaderMapper.getVisitRecordList(pageVo, account);

            PageData<VisitorRecordAO> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(pageNum);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = pageNum * pageSize;
            pageEntity.setHasNext(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
