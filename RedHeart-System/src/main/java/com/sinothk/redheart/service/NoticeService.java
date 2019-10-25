package com.sinothk.redheart.service;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.NoticeEntity;
import com.sinothk.redheart.domain.NoticeVo;

import java.util.List;

public interface NoticeService {

    ResultData<Boolean> add(NoticeEntity noticeEntity);

    ResultData<Boolean> addRead(Long noticeId, Long account);

    ResultData<PageData<List<NoticeVo>>> getAllNoticeList(Long account, int pageNum, int pageSize);
}
