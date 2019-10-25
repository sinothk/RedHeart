package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.LoginRecordEntity;
import com.sinothk.redheart.domain.NoticeEntity;
import org.springframework.stereotype.Repository;

@Repository("noticeMapper")
public interface NoticeMapper extends BaseMapper<NoticeEntity> {

//    @Select("select keep_account from tb_comm_init_account")
//    Set<Long> getSysKeepAccountSet();
//
//    @Select("select u_account from tb_comm_user ORDER BY id ASC")
//    Set<Long> getUserAccountSet();


}
