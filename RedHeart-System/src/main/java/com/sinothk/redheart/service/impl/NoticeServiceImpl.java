package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.*;
import com.sinothk.redheart.repository.NoticeMapper;
import com.sinothk.redheart.repository.NoticeReaderMapper;
import com.sinothk.redheart.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Resource(name = "noticeMapper")
    private NoticeMapper noticeMapper;

    @Resource(name = "noticeReaderMapper")
    private NoticeReaderMapper noticeReaderMapper;

    @Override
    public ResultData<Boolean> add(NoticeEntity noticeEntity) {
        try {
            noticeMapper.insert(noticeEntity);
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<NoticeAo>> getAllNoticeList(Long account, int pageNum, int pageSize) {
        try {
            Page<NoticeAo> pageVo = new Page<>(pageNum, pageSize);
            IPage<NoticeAo> pageInfo = noticeMapper.getAllNoticeList(pageVo, account);

            PageData<NoticeAo> pageEntity = new PageData<>();
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

    @Override
    public ResultData<PageData<NoticeReaderVo>> getNoticeReaderList(Long noticeId, int pageNum, int pageSize) {
        try {
            Page<NoticeReaderVo> pageVo = new Page<>(pageNum, pageSize);
            IPage<NoticeReaderVo> pageInfo = noticeMapper.getNoticeReaderList(pageVo, noticeId);

            PageData<NoticeReaderVo> pageEntity = new PageData<>();
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

    @Override
    public ResultData<Boolean> addRead(Long noticeId, Long account) {
        try {

            QueryWrapper<NoticeReaderEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(NoticeReaderEntity::getNoticeId, noticeId).eq(NoticeReaderEntity::getReaderAccount, account);

            NoticeReaderEntity noticeReaderDbEntity = noticeReaderMapper.selectOne(wrapper);
            if (noticeReaderDbEntity == null) {
                NoticeReaderEntity noticeReaderVo = new NoticeReaderEntity();
                noticeReaderVo.setReaderAccount(account);
                noticeReaderVo.setNoticeId(noticeId);
                noticeReaderVo.setReadTime(new Date());
                noticeReaderMapper.insert(noticeReaderVo);
            }
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }


}
