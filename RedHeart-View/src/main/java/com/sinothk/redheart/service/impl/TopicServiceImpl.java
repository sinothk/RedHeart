package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.IdUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.jpush.pushbyjpush.JPushEntity;
import com.sinothk.jpush.pushbyjpush.JPushHelper;
import com.sinothk.redheart.domain.*;
import com.sinothk.redheart.repository.FriendMapper;
import com.sinothk.redheart.repository.TopicMapper;
import com.sinothk.redheart.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

    @Resource(name = "topicMapper")
    private TopicMapper topicMapper;
    @Resource(name = "friendMapper")
    private FriendMapper friendMapper;

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

            // 通知关注人
            new Thread(() -> {
                try {
                    TopicAo topicAo = topicMapper.getTopicInfo(topicEntity.getTopicId());

                    // 获取我的粉丝用户
                    QueryWrapper<FriendRelationshipEntity> fansWrapper = new QueryWrapper<>();
                    fansWrapper.lambda().eq(FriendRelationshipEntity::getLikedAccount, topicEntity.getAccount());
                    List<FriendRelationshipEntity> fansList = friendMapper.selectList(fansWrapper);
                    ArrayList<String> aliasList = new ArrayList<>();
                    for (FriendRelationshipEntity fans : fansList) {
                        aliasList.add(String.valueOf(fans.getLikingAccount()));
                    }
                    String data = JPushEntity.createData(JPushEntity.MSG_TYPE_TOPIC_NEW, topicAo);
                    JPushHelper.pushByAlias(aliasList, "新话题", "你关注的人发布了新话题，快去看看吧 ... ", data);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<TopicAo>> getTopicFromUserPageList(Long account, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getTopicFromUserPageList(pageVo, account);

            PageData<TopicAo> pageEntity = new PageData<>();
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
    public ResultData<PageData<TopicAo>> getTopicFromILikeUserPageList(Long account, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getTopicFromILikeUserPageList(pageVo, account);

            PageData<TopicAo> pageEntity = new PageData<>();
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
    public ResultData<PageData<TopicAo>> getNewTopicPageList(int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getNewTopicPageList(pageVo);

            PageData<TopicAo> pageEntity = new PageData<>();
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
    public ResultData<PageData<TopicAo>> getTopicByThemePageList(String themeCode, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getTopicByThemePageList(pageVo, themeCode);

            PageData<TopicAo> pageEntity = new PageData<>();
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
    public ResultData<PageData<TopicAo>> getTopicWhereUserPraisePageList(String targetAccount, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getTopicWhereUserPraisePageList(pageVo, targetAccount);

            PageData<TopicAo> pageEntity = new PageData<>();
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
