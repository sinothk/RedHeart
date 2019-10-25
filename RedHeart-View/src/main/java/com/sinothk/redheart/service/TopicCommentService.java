package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicCommentEntity;

public interface TopicCommentService {
    ResultData<Boolean> add(TopicCommentEntity topicCommentEntity);
}
