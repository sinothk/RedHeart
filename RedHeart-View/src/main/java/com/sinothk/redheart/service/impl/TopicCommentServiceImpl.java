package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.push.jpush.JPushEntity;
import com.sinothk.base.push.jpush.JPushHelper;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicCommentEntity;
import com.sinothk.redheart.domain.TopicCommentVo;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.repository.TopicCommentMapper;
import com.sinothk.redheart.repository.TopicMapper;
import com.sinothk.redheart.service.TopicCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("topicCommentService")
public class TopicCommentServiceImpl implements TopicCommentService {

    @Resource(name = "topicCommentMapper")
    private TopicCommentMapper topicCommentMapper;

    @Resource(name = "topicMapper")
    private TopicMapper topicMapper;

    @Override
    public ResultData<Boolean> add(TopicCommentEntity topicCommentEntity) {
        try {
            topicCommentMapper.insert(topicCommentEntity);

            UpdateWrapper<TopicEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda().eq(TopicEntity::getTopicId, topicCommentEntity.getTopicId());

            // 更新话题的时间，为了排序
            TopicEntity topicEntity = new TopicEntity();
            topicEntity.setTopicId(topicCommentEntity.getTopicId());
            topicEntity.setUpdateTime(new Date());
            topicMapper.update(topicEntity, updateWrapper);

            // 通知话题发布人
            QueryWrapper<TopicEntity> topicWrapper = new QueryWrapper<>();
            topicWrapper.lambda().eq(TopicEntity::getTopicId, topicCommentEntity.getTopicId());
            TopicEntity topicDbEntity = topicMapper.selectOne(topicWrapper);
            if (!topicCommentEntity.getSendAccount().equals(topicDbEntity.getAccount())) {
                // 评论人不是发布人则推送提醒
                new Thread(() -> {
                    try {
                        TopicAo topicAo = topicMapper.getTopicInfo(topicEntity.getTopicId());

                        String alias = String.valueOf(topicDbEntity.getAccount());
                        String data =JPushEntity.createData(JPushEntity.MSG_TYPE_COMMENT, topicAo);
                        JPushHelper.pushByAlias(alias, "话题新评论", "有人评论了你的话题，快去看看吧 ... ", data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }

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
    public ResultData<PageData<TopicCommentVo>> getTopicCommentPageList(String topicId, int pageNum, int pageSize) {
        try {
            Page<TopicCommentVo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicCommentVo> pageInfo = topicCommentMapper.getTopicCommentPageList(pageVo, topicId);

            PageData<TopicCommentVo> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(pageNum);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = pageNum * pageSize;
            pageEntity.setHasNext(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
