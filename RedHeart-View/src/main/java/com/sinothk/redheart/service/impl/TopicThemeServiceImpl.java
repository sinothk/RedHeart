package com.sinothk.redheart.service.impl;

import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.IdUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.domain.KeyValue;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.domain.TopicThemeEntity;
import com.sinothk.redheart.repository.TopicMapper;
import com.sinothk.redheart.repository.TopicThemeMapper;
import com.sinothk.redheart.service.TopicService;
import com.sinothk.redheart.service.TopicThemeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("topicThemeService")
public class TopicThemeServiceImpl implements TopicThemeService {

    @Resource(name = "topicThemeMapper")
    private TopicThemeMapper topicThemeMapper;

    @Override
    public ResultData<Boolean> add(TopicThemeEntity topicThemeEntity) {
        try {
            // 处理topicId
            topicThemeMapper.insert(topicThemeEntity);
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }


//    @Override
//    public ResultData<Boolean> addTopic(TopicEntity topicEntity) {
//        try {
//            // 处理topicId
//            if (StringUtil.isEmpty(topicEntity.getTopicId())) {
//                topicEntity.setTopicId(IdUtil.generateShortUuid());
//            }
//
//            topicEntity.setTopicTheme(KeyValue.getTopicValue(topicEntity.getTopicTheme()));
//
//            Date pubTime = new Date();
//            topicEntity.setCreateTime(pubTime);
//            topicEntity.setUpdateTime(pubTime);
//            topicMapper.insert(topicEntity);
//
//            return ResultData.success(true);
//        } catch (Exception e) {
//            return ResultData.error(e.getCause().getMessage());
//        }
//    }

//    @Override
//    public ResultData<PageData<List<TopicAo>>> getTopicFromMePageList(Long account, int pageNum, int pageSize) {
//        try {
//            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);
//            IPage<TopicAo> pageInfo = topicMapper.getTopicFromMePageList(pageVo, account);
//
//            PageData<List<TopicAo>> pageEntity = new PageData<>();
//            pageEntity.setPageSize(pageSize);
//            pageEntity.setPageNum(pageNum);
//
//            pageEntity.setData(pageInfo.getRecords());
//            pageEntity.setTotal((int) pageInfo.getTotal());
//            int currSize = pageNum * pageSize;
//            pageEntity.setHasMore(currSize < pageInfo.getTotal());
//
//            return ResultData.success(pageEntity);
//        } catch (Exception e) {
//            return ResultData.error(e.getCause().getMessage());
//        }
//    }
}
