package com.sinothk.redheart.service.impl;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.NoticeEntity;
import com.sinothk.redheart.repository.NoticeMapper;
import com.sinothk.redheart.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Resource(name = "noticeMapper")
    private NoticeMapper noticeMapper;

    @Override
    public ResultData<Boolean> add(NoticeEntity noticeEntity) {
        try {
            noticeMapper.insert(noticeEntity);
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
