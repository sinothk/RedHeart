package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;

import java.util.List;

public interface TopicService {

    ResultData<Boolean> addTopic(TopicEntity topicEntity);

    ResultData<PageData<TopicAo>> getTopicFromUserPageList(Long account, int pageNum, int pageSize);

    ResultData<PageData<TopicAo>> getTopicFromILikeUserPageList(Long valueOf, int sex, int pageNum, int pageSize);

    ResultData<PageData<TopicAo>> getNewTopicPageList(int sex, int pageNum, int pageSize);

    ResultData<PageData<TopicAo>> getTopicByThemePageList(String themeCode, int pageNum, int pageSize);

    ResultData<PageData<TopicAo>> getTopicWhereUserPraisePageList(String targetAccount, int pageNum, int pageSize);

    ResultData<List<TopicAo>> findTopicContent(String account, String keyword);
}
