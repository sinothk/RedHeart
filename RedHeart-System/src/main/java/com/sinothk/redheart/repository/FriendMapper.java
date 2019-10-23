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

            "FROM tb_comm_user tcu, \n" +
            "\t(\n" +
            "\tSELECT t2.* \n" +
            "\tFROM \n" +
            "\t\t(SELECT f.* FROM tb_comm_friends f WHERE f.`liking_account` = ${account}) t1, \n" +
            "\t\t(SELECT f.* FROM tb_comm_friends f WHERE f.`liked_account` = ${account}) t2 \n" +
            "\tWHERE t1.liked_account = t2.liking_account GROUP BY t2.liking_account\n" +
            "\t) tcf \n" +
            "WHERE tcu.`u_account` = tcf.`liking_account` ORDER BY tcf.like_time DESC")
    IPage<FriendEntity> getFriendsList(IPage page, @Param("account") Long account);

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
            "(SELECT COUNT(f.liking_account) FROM tb_comm_friends f WHERE (f.`liking_account` = ${loginAccount} AND f.`liked_account` = ${targetAccount})) likingNum, " +
            "(SELECT COUNT(f.liking_account) FROM tb_comm_friends f WHERE (f.`liking_account` = ${targetAccount} AND f.`liked_account` = ${loginAccount})) likedNum " +
            "FROM tb_comm_user tcu WHERE tcu.`u_account` = ${targetAccount}")
    FriendEntity getUserInfo(@Param("loginAccount") String loginAccount, @Param("targetAccount") String targetAccount);
}
