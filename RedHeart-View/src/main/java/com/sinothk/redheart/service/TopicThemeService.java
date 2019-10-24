package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicThemeEntity;

public interface TopicThemeService {

    ResultData<Boolean> add(TopicThemeEntity topicThemeEntity);
}
