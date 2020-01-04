package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.redheart.domain.UserAO;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("userMapper")
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("select keep_account from tb_comm_init_account")
    Set<Long> getSysKeepAccountSet();

    @Select("select u_account from tb_comm_user ORDER BY id ASC")
    Set<Long> getUserAccountSet();

    IPage<UserAO> getNearbyUserPageList(Page<UserVo> pageVo, @Param("userVo") UserVo userVo);

    @Select("SELECT " +

            "tcu.id as id, " +
            "tcu.u_account as account, " +
            "tcu.u_email as email, " +
            "tcu.user_name as userName, " +
            "tcu.u_nickname as nickname, " +
            "tcu.u_avatar as avatar, " +
            "tcu.u_sex as sex, " +
            "tcu.u_idcard as idCard, " +
            "tcu.u_phone_num as phoneNum, " +
            "tcu.user_status as userStatus, " +
            "tcu.user_borthday as userBorthday, " +
            "tcu.login_time as loginTime, " +

            "tu.topicNum" +

            " FROM " +
            " tb_comm_user tcu, " +
            "(SELECT account, COUNT(id) AS topicNum FROM tb_app_topic GROUP BY account) tu " +
            "WHERE tcu.`u_account` = tu.account ORDER BY tu.topicNum DESC, tcu.login_time DESC")
    IPage<UserEntity> getUsersByTopsTopicPageList(Page<UserEntity> pageVo);
}
