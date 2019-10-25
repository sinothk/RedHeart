package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.NoticeEntity;

public interface NoticeService {
    ResultData<Boolean> add(NoticeEntity noticeEntity);
}
