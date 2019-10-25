package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicCommentEntity;
import com.sinothk.redheart.domain.TopicCommentVo;

import java.util.List;

public interface TopicCommentService {
    ResultData<Boolean> add(TopicCommentEntity topicCommentEntity);

    ResultData<Boolean> del(Long account, Long comId);

    ResultData<PageData<List<TopicCommentVo>>> getTopicCommentPageList(String topicId, int pageNum, int pageSize);
}
