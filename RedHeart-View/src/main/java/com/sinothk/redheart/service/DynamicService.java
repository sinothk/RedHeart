package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicAo;

import java.util.List;

public interface DynamicService {

    ResultData<PageData<TopicAo>> findDynamicByAccount(String account, int pageNum, int pageSize);
}
