package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.WxUserOpenIdEntity;
import org.springframework.stereotype.Repository;

@Repository("wxUserOpenIdMapper")
public interface WxUserOpenIdMapper extends BaseMapper<WxUserOpenIdEntity> {

//    @Select("select u_account from tb_comm_user")
//    Set<Long> getUserAccountSet();
}
