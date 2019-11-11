package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;

import java.util.List;

public interface TopicService {

    ResultData<Boolean> addTopic(TopicEntity topicEntity);

    ResultData<PageData<List<TopicAo>>> getTopicFromUserPageList(Long account, int pageNum, int pageSize);

    ResultData<PageData<List<TopicAo>>> getTopicFromILikePageList(Long valueOf, int pageNum, int pageSize);

    ResultData<PageData<List<TopicAo>>> getNewTopicPageList(int pageNum, int pageSize);

    ResultData<PageData<List<TopicAo>>> getTopicByThemePageList(String themeCode, int pageNum, int pageSize);

    ResultData<PageData<List<TopicAo>>> getTopicWhereUserPraisePageList(String targetAccount, int pageNum, int pageSize);

    ResultData<List<TopicAo>> findTopicContent(String account, String keyword);
}
