package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.push.jpush.JPushEntity;
import com.sinothk.base.push.jpush.JPushHelper;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.domain.TopicPraiseEntity;
import com.sinothk.redheart.repository.TopicMapper;
import com.sinothk.redheart.repository.TopicPraiseMapper;
import com.sinothk.redheart.service.TopicPraiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("topicPraiseService")
public class TopicPraiseServiceImpl extends ServiceImpl<TopicPraiseMapper, TopicPraiseEntity> implements TopicPraiseService {

    @Resource(name = "topicPraiseMapper")
    private TopicPraiseMapper topicPraiseMapper;
    @Resource(name = "topicMapper")
    private TopicMapper topicMapper;

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

            // 通知话题发布人
            QueryWrapper<TopicEntity> topicWrapper = new QueryWrapper<>();
            topicWrapper.lambda().eq(TopicEntity::getTopicId, entity.getTopicId());
            TopicEntity topicEntity = topicMapper.selectOne(topicWrapper);

            if (!entity.getAccount().equals(topicEntity.getAccount())) {
                // 点赞人不是发布人则推送提醒
                new Thread(() -> {
                    try {
                        TopicAo topicAo = topicMapper.getTopicInfo(topicEntity.getTopicId());
                        //
                        String alias = String.valueOf(topicEntity.getAccount());
                        String data = JPushEntity.createData(JPushEntity.MSG_TYPE_PRAISE, topicAo);
                        JPushHelper.pushByAlias(alias, "话题新点赞", "有人点赞了你的话题，快去看看吧 ... ", data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }).start();
            }

            return ResultData.success(entity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
