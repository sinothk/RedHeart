package com.sinothk.redheart.service.impl;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.IdUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.repository.TopicMapper;
import com.sinothk.redheart.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

    @Resource(name = "topicMapper")
    private TopicMapper topicMapper;

    @Override
    public ResultData<Boolean> addTopic(TopicEntity topicEntity) {
        try {
            // 处理topicId
            if (StringUtil.isEmpty(topicEntity.getTopicId())) {
                topicEntity.setTopicId(IdUtil.generateShortUuid());
            }

            Date pubTime = new Date();
            topicEntity.setCreateTime(pubTime);
            topicEntity.setUpdateTime(pubTime);
            topicMapper.insert(topicEntity);

            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
