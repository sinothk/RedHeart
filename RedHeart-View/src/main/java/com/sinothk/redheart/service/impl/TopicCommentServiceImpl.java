package com.sinothk.redheart.service.impl;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicCommentEntity;
import com.sinothk.redheart.repository.TopicCommentMapper;
import com.sinothk.redheart.service.TopicCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("topicCommentService")
public class TopicCommentServiceImpl implements TopicCommentService {

    @Resource(name = "topicCommentMapper")
    private TopicCommentMapper topicCommentMapper;

    @Override
    public ResultData<Boolean> add(TopicCommentEntity topicCommentEntity) {
        try {
            topicCommentMapper.insert(topicCommentEntity);
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
