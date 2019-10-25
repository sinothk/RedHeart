package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicCommentEntity;
import com.sinothk.redheart.domain.TopicCommentVo;
import com.sinothk.redheart.repository.TopicCommentMapper;
import com.sinothk.redheart.service.TopicCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public ResultData<PageData<List<TopicCommentVo>>> getTopicCommentPageList(String topicId, int pageNum, int pageSize) {
        try {
            Page<TopicCommentVo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicCommentVo> pageInfo = topicCommentMapper.getTopicCommentPageList(pageVo, topicId);

            PageData<List<TopicCommentVo>> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(pageNum);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = pageNum * pageSize;
            pageEntity.setHasMore(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
