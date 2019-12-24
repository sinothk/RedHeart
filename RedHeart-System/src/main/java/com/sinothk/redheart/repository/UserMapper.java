package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.UserEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("userMapper")
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("select keep_account from tb_comm_init_account")
    Set<Long> getSysKeepAccountSet();

    @Select("select u_account from tb_comm_user ORDER BY id ASC")
    Set<Long> getUserAccountSet();

//    @Select("SELECT *, db_living.GETDISTANCE( login_lat, login_lon, ${centerLat}, ${centerLng}) AS distance " +
//            "FROM  tb_comm_user " +
//            " WHERE 1 HAVING distance < 100000 ORDER BY distance")
//    IPage<UserEntity> getNearbyUserPageList(Page<UserAO> pageVo, @Param("centerLat") Double centerLat, @Param("centerLng") Double centerLng);

//    @Select("SELECT *,  COUNT(id) as distance " +
//            "FROM  tb_comm_user " +
//            " ORDER BY distance") @Param("centerLat") Double centerLat, @Param("centerLng") Double centerLng

//    List<UserAO> getNearbyUserPageList();
}
