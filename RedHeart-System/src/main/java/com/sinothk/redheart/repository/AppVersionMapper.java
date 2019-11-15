package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.AdvertiseEntity;
import com.sinothk.redheart.domain.AppVersionEntity;
import org.springframework.stereotype.Repository;

@Repository("appVersionMapper")
public interface AppVersionMapper extends BaseMapper<AppVersionEntity> {

//    @Select("select keep_account from tb_comm_init_account")
//    Set<Long> getSysKeepAccountSet();
//
//    @Select("select u_account from tb_comm_user ORDER BY id ASC")
//    Set<Long> getUserAccountSet();



}
