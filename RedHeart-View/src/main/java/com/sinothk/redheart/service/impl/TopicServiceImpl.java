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
import com.sinothk.redheart.domain.FriendRelationshipEntity;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
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

            int sexType;
            if ("TOPIC_THEME_SEX_WOMAN".equals(topicEntity.getTopicTheme())) {
                sexType = 0; // 女性可见
            } else if ("TOPIC_THEME_SEX_MAN".equals(topicEntity.getTopicTheme())) {
                sexType = 1; // 男性可见
            } else {
                sexType = -1; // 所有性别
            }
            topicEntity.setSexType(sexType);
            // 保存
            topicMapper.insert(topicEntity);

            // 通知所有粉丝
            new Thread(() -> {
                try {
                    TopicAo topicAo = topicMapper.getTopicInfo(topicEntity.getTopicId());

                    // 获取我的粉丝用户
                    List<FriendRelationshipEntity> fansList;

                    if (sexType == 0 || sexType == 1) {
                        // 区分性别
                        fansList = friendMapper.selectSexTypeUserList(topicEntity.getAccount(), sexType);
                    } else {
                        // 不区分性别，则通知所有粉丝
                        QueryWrapper<FriendRelationshipEntity> fansWrapper = new QueryWrapper<>();
                        fansWrapper.lambda().eq(FriendRelationshipEntity::getLikedAccount, topicEntity.getAccount());
                        fansList = friendMapper.selectList(fansWrapper);
                    }

                    if (fansList == null || fansList.size() == 0) {
                        return;
                    }

                    ArrayList<String> aliasList = new ArrayList<>();
                    for (FriendRelationshipEntity fans : fansList) {
                        aliasList.add(String.valueOf(fans.getLikingAccount()));
                    }
                    String data = JPushEntity.createData(JPushEntity.MSG_TYPE_TOPIC_NEW, topicAo);
                    JPushHelper.pushByAlias(aliasList, "心跳新话题", "你关注的人发布了新话题，快去看看吧 ... ", data);

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
    public ResultData<PageData<TopicAo>> getTopicFromUserPageList(Long account, boolean isLoginUser, int sex, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);

            IPage<TopicAo> pageInfo;
            if (isLoginUser) {
                pageInfo = topicMapper.getTopicFromUserPageList(pageVo, account);
            } else {
                pageInfo = topicMapper.getTopicFromUserBySexPageList(pageVo, account, sex);
            }

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
    public ResultData<PageData<TopicAo>> getTopicFromILikeUserPageList(Long account, int sex, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getTopicFromILikeUserPageList(pageVo, account, sex);

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
    public ResultData<PageData<TopicAo>> getNewTopicPageList(int sex, int pageNum, int pageSize) {
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
    public ResultData<PageData<TopicAo>> getTopicByThemePageList(String account, String themeCode, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);

            IPage<TopicAo> pageInfo;
            if ("TOPIC_THEME_PHOTO".equals(themeCode)) {
                pageInfo = topicMapper.getTopicByThemeAccountPageList(pageVo, themeCode, account);
            } else {
                pageInfo = topicMapper.getTopicByThemePageList(pageVo, themeCode);
            }

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

    @Override
    public ResultData<PageData<TopicAo>> getHotTopicPageList(int sex, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<TopicAo> pageInfo = topicMapper.getHotTopicPageList(pageVo, sex);

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
}
