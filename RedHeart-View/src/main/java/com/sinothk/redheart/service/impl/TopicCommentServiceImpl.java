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

    @Override
    public ResultData<Boolean> del(Long account, Long comId) {
        try {
            TopicCommentEntity tcEntity = topicCommentMapper.selectById(comId);
            if (tcEntity == null) {
                return ResultData.error("数据不存在");
            }

            if (!tcEntity.getSendAccount().equals(account)) {
                return ResultData.error("只有评论人才能删除");
            } else {
                topicCommentMapper.deleteById(comId);
            }
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
