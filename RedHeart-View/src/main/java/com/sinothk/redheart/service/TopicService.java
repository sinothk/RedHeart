package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.domain.TopicVo;
import com.sinothk.redheart.domain.UserEntity;

public interface TopicService {

    ResultData<Boolean> addTopic(TopicEntity topicEntity);

}
