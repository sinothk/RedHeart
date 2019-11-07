package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicPraiseEntity;
import com.sinothk.redheart.repository.TopicPraiseMapper;
import com.sinothk.redheart.service.TopicPraiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("topicPraiseService")
public class TopicPraiseServiceImpl extends ServiceImpl<TopicPraiseMapper, TopicPraiseEntity> implements TopicPraiseService {

    @Resource(name = "topicPraiseMapper")
    private TopicPraiseMapper topicPraiseMapper;

    @Override
    public ResultData<TopicPraiseEntity> add(TopicPraiseEntity entity) {
        try {
            QueryWrapper<TopicPraiseEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(TopicPraiseEntity::getAccount, entity.getAccount())
                    .eq(TopicPraiseEntity::getTopicId, entity.getTopicId());

            TopicPraiseEntity dbEntity = topicPraiseMapper.selectOne(wrapper);
            if (dbEntity != null) {
                entity.setId(dbEntity.getId());
                entity.setPraiseNum(dbEntity.getPraiseNum() + 1);
            } else {
                entity.setPraiseNum(1);
            }
            saveOrUpdate(entity);

            // 返回总量
            int pNum = topicPraiseMapper.selectTopicPraiseNum(entity.getTopicId());
            entity.setPraiseNum(pNum);
            return ResultData.success(entity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
