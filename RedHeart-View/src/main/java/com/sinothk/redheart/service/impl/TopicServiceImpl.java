package com.sinothk.redheart.service.impl;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.IdUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.domain.KeyValue;
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
            topicEntity.setBizType(KeyValue.BIZ_TYPE_TOPIC);
            // 处理topicId
            if (StringUtil.isEmpty(topicEntity.getTopicId())) {
                topicEntity.setTopicId(IdUtil.generateShortUuid());
            }
            topicEntity.setCreateTime(new Date());
            topicMapper.insert(topicEntity);

            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
