package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicThemeAo;
import com.sinothk.redheart.domain.TopicThemeEntity;
import com.sinothk.redheart.domain.TopicThemeUserEntity;

import java.util.List;

public interface TopicThemeService {

    ResultData<Boolean> add(TopicThemeEntity topicThemeEntity);

    ResultData<List<TopicThemeEntity>> getAllTopicThemeList();


    ResultData<String> likeTheme(TopicThemeUserEntity ttuEntity);

    ResultData<List<TopicThemeAo>> getMyTopicThemeList(Long account);
}
