package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.IdUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.repository.TopicMapper;
import com.sinothk.redheart.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

    @Resource(name = "topicMapper")
    private TopicMapper topicMapper;

    @Override
    public ResultData<Boolean> addTopic(TopicEntity topicEntity) {
        try {
            // 处理topicId
            if (StringUtil.isEmpty(topicEntity.getTopicId())) {
                topicEntity.setTopicId(IdUtil.generateShortUuid());
            }

            Date pubTime = new Date();
            topicEntity.setCreateTime(pubTime);
            topicEntity.setUpdateTime(pubTime);
            topicMapper.insert(topicEntity);

            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<List<TopicAo>>> getTopicFromUserPageList(Long account, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getTopicFromUserPageList(pageVo, account);

            PageData<List<TopicAo>> pageEntity = new PageData<>();
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

    @Override
    public ResultData<PageData<List<TopicAo>>> getTopicFromILikeUserPageList(Long account, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getTopicFromILikeUserPageList(pageVo, account);

            PageData<List<TopicAo>> pageEntity = new PageData<>();
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

    @Override
    public ResultData<PageData<List<TopicAo>>> getNewTopicPageList(int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getNewTopicPageList(pageVo);

            PageData<List<TopicAo>> pageEntity = new PageData<>();
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

    @Override
    public ResultData<PageData<List<TopicAo>>> getTopicByThemePageList(String themeCode, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getTopicByThemePageList(pageVo, themeCode);

            PageData<List<TopicAo>> pageEntity = new PageData<>();
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

    @Override
    public ResultData<PageData<List<TopicAo>>> getTopicWhereUserPraisePageList(String targetAccount, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getTopicWhereUserPraisePageList(pageVo, targetAccount);

            PageData<List<TopicAo>> pageEntity = new PageData<>();
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

    @Override
    public ResultData<List<TopicAo>> findTopicContent(String account, String keyword) {
        try {
            List<TopicAo> userList = topicMapper.findTopicContent(keyword);
            return ResultData.success(userList);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
