package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;

import java.util.List;

public interface TopicService {

    ResultData<Boolean> addTopic(TopicEntity topicEntity);

    ResultData<PageData<List<TopicAo>>> getTopicFromMePageList(Long account, int pageNum, int pageSize);

    ResultData<PageData<List<TopicAo>>> getTopicFromILikePageList(Long valueOf, int pageNum, int pageSize);
}
