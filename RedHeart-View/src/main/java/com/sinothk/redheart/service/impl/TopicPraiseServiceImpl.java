package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinothk.base.entity.ResultData;
import com.sinothk.jpush.pushbyjpush.JPushEntity;
import com.sinothk.jpush.pushbyjpush.JPushHelper;
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

            new Thread(() -> {
                // 通知话题发布人
                QueryWrapper<TopicEntity> topicWrapper = new QueryWrapper<>();
                topicWrapper.lambda().eq(TopicEntity::getTopicId, entity.getTopicId());
                TopicEntity topicEntity = topicMapper.selectOne(topicWrapper);

                String alias = String.valueOf(topicEntity.getAccount());
                String data = JPushEntity.createData(JPushEntity.MSG_TYPE_PRAISE, topicEntity);
                JPushHelper.pushByAlias(alias, "心跳关注提醒", "你新增加了一位爱慕人 ... ", data);
            }).start();

            return ResultData.success(entity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
