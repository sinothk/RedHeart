package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.LoginRecordEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("loginReordMapper")
public interface LoginReordMapper extends BaseMapper<LoginRecordEntity> {

//    @Select("select keep_account from tb_comm_init_account")
//    Set<Long> getSysKeepAccountSet();
//
//    @Select("select u_account from tb_comm_user ORDER BY id ASC")
//    Set<Long> getUserAccountSet();


}
