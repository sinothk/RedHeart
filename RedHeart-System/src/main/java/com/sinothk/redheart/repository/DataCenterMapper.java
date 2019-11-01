package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.redheart.domain.DataCenterEntity;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.UserLoginAO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
            "(" +
            "SELECT account FROM tb_comm_login_record WHERE DATE_FORMAT(login_time, '%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') GROUP BY DATE_FORMAT(login_time, '%Y-%m-%d'), account" +
            ")  ORDER BY login_time DESC")
    IPage<UserLoginAO> getTodayLoginUserPageList(Page<UserEntity> pageVo);

    @Select("SELECT " +
            "id AS id, u_account AS account, u_email AS email, user_name AS userName, u_nickname AS nickname, u_avatar AS avatar, u_sex AS sex, u_idcard AS idCard, u_phone_num AS phoneNum, user_status AS userStatus, user_borthday AS userBorthday, login_lat AS loginLat, login_lon AS loginLon, login_time AS loginTime \n" +
            "FROM tb_comm_user WHERE u_account IN " +
            "(" +
//                " SELECT account FROM tb_comm_login_record WHERE login_time >= DATE_FORMAT(DATE_SUB(DATE_SUB(NOW(), INTERVAL WEEKDAY(NOW()) DAY), INTERVAL 1 WEEK), '%Y-%m-%d') GROUP BY DATE_FORMAT(login_time, '%Y-%m-%d'), account" +
            "SELECT account FROM tb_comm_login_record WHERE MONTH(login_time) = MONTH(CURDATE()) AND WEEK(login_time) = WEEK(CURDATE())  GROUP BY account" +
            ") ORDER BY login_time DESC")
    IPage<UserLoginAO> getWeekLoginUserPageList(Page<UserEntity> pageVo);

    /**
     * 当前月登录的用户信息
     *
     * @param pageVo
     * @return
     */
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
            "(" +
            "SELECT account FROM tb_comm_login_record WHERE MONTH(login_time) = MONTH(CURDATE())  GROUP BY account" +
            ")  ORDER BY login_time DESC")
    IPage<UserLoginAO> getThisMonthLoginUserPageList(Page<UserEntity> pageVo);

    /**
     * 统计某一年每个月份登录数据
     *
     * @param yearStr
     * @return
     */
    @Select("SELECT " +
            " MONTH as mouthNum, " +
            " COUNT(*) AS total" +
            " FROM " +
            "( SELECT DATE_FORMAT(login_time, '%m') MONTH FROM tb_comm_login_record WHERE DATE_FORMAT(login_time, '%Y') = ${yearStr}) a " +
            " GROUP BY MONTH ORDER BY MONTH")
    ArrayList<DataCenterEntity> getYearLoginUserPageList(@Param("yearStr") String yearStr);
}
