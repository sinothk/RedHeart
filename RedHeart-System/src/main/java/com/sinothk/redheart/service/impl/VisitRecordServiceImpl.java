package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinothk.base.entity.ResultData;
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
                entity.setVisitTime(new Date());
                noticeReaderMapper.insert(entity);
            } else {
                if (dbEntity.getVisitNum() == null) {
                    dbEntity.setVisitNum(0);
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
}
