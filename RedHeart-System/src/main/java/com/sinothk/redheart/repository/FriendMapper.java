package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.domain.FriendRelationshipEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("friendMapper")
public interface FriendMapper extends BaseMapper<FriendRelationshipEntity> {

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
            "tcf.like_time as likeTime " +
            "FROM tb_comm_user tcu, (SELECT f.* FROM tb_comm_friends f WHERE f.`liking_account` = ${account}) tcf \n" +
            "WHERE tcu.`u_account` = tcf.`liked_account` ORDER BY tcf.like_time DESC")
    IPage<FriendEntity> getLikeUserList(IPage page, @Param("account") Long account);


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
            "tcf.like_time as likeTime " +
            "FROM tb_comm_user tcu, (SELECT f.* FROM tb_comm_friends f WHERE f.`liked_account` = ${account}) tcf \n" +
            "WHERE tcu.`u_account` = tcf.`liking_account` ORDER BY tcf.like_time DESC")
    IPage<FriendEntity> getFensUserList(IPage page, @Param("account") Long account);
}
