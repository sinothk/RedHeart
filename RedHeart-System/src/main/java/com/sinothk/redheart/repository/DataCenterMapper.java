package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.UserLoginAOEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("dataCenterMapper")
public interface DataCenterMapper {

    @Select("SELECT " +
            "id as id, " +
            "u_account as account, " +
            "u_email as email, " +
            "user_name as userName, " +
            "u_nickname as nickname, " +
            "u_avatar as avatar, " +
            "u_sex as sex, " +
            "u_idcard as idCard, " +
            "u_phone_num as phoneNum, " +
            "user_status as userStatus, " +
            "user_borthday as userBorthday, " +
            "login_lat as loginLat, " +
            "login_lon as loginLon, " +
            "login_time as loginTime " +

            " FROM " +
                " tb_comm_user " +
            " WHERE u_account IN " +
                "(SELECT account FROM tb_comm_login_record WHERE DATE_FORMAT(login_time, '%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') " +
                    "GROUP BY DATE_FORMAT(login_time, '%Y-%m-%d'), account) " +
            " ORDER BY login_time DESC")
    IPage<UserLoginAOEntity> getTodayLoginUserPageList(Page<UserEntity> pageVo);
}
