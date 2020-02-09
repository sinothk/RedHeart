package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.repository.DynamicMapper;
import com.sinothk.redheart.service.DynamicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("dynamicService")
public class DynamicServiceImpl implements DynamicService {

    @Resource(name = "dynamicMapper")
    private DynamicMapper dynamicMapper;

    @Override
    public ResultData<PageData<TopicAo>> findDynamicByAccount(String account, int pageNum, int pageSize) {
        try {
            Page<TopicAo> pageVo = new Page<>(pageNum, pageSize);

            IPage<TopicAo> pageInfo = dynamicMapper.findDynamicByAccount(pageVo, account);


//            if (isLoginUser) {
//                pageInfo = topicMapper.getTopicFromUserPageList(pageVo, account);
//            } else {
//                pageInfo = dynamicMapper.getTopicFromUserBySexPageList(pageVo, account, sex);
//            }

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
