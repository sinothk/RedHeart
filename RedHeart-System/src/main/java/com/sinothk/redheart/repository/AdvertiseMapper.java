package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.AdvertiseEntity;
import com.sinothk.redheart.domain.NoticeReaderEntity;
import org.springframework.stereotype.Repository;

@Repository("advertiseMapper")
public interface AdvertiseMapper extends BaseMapper<AdvertiseEntity> {

//    @Select("select keep_account from tb_comm_init_account")
//    Set<Long> getSysKeepAccountSet();
//
//    @Select("select u_account from tb_comm_user ORDER BY id ASC")
//    Set<Long> getUserAccountSet();


}
