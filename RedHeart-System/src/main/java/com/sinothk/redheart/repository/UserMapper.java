package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.UserEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("userMapper")
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("select u_account from tb_user")
    Set<Long> getUserAccountSet();
}
