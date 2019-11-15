package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.NoticeEntity;
import com.sinothk.redheart.domain.NoticeReaderVo;
import com.sinothk.redheart.domain.NoticeAo;

import java.util.List;

public interface NoticeService {

    ResultData<Boolean> add(NoticeEntity noticeEntity);

    ResultData<Boolean> addRead(Long noticeId, Long account);

    ResultData<PageData<NoticeAo>> getAllNoticeList(Long account, int pageNum, int pageSize);

    ResultData<PageData<NoticeReaderVo>> getNoticeReaderList(Long noticeId, int pageNum, int pageSize);
}
