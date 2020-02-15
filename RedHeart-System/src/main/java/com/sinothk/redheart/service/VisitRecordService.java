package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.VisitorRecordEntity;

public interface VisitRecordService {

    ResultData<Boolean> add(VisitorRecordEntity entity);

//    ResultData<Boolean> addRead(Long noticeId, Long account);
//
//    ResultData<PageData<NoticeAo>> getAllNoticeList(Long account, int pageNum, int pageSize);
//
//    ResultData<PageData<NoticeReaderVo>> getNoticeReaderList(Long noticeId, int pageNum, int pageSize);
}
