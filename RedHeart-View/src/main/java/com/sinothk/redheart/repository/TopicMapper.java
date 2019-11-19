package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("topicMapper")
public interface TopicMapper extends BaseMapper<TopicEntity> {

    @Select("SELECT " +

            "topic.id as id, " +
            "topic.topic_id as topicId, " +
            "topic.account as account, " +
            "topic.topic_title as topicTitle, " +
            "topic.topic_content as topicContent, " +
            "topic.loc_lat as locLat, " +
            "topic.loc_lng as locLng, " +
            "topic.loc_address as locAddress, " +
            "topic.create_time as createTime, " +
            "topic.update_time as updateTime, " +
            "topic.topic_img as topicImg, " +
            "topic.topic_cover as topicCover, " +

            "(SELECT SUM(praise_num) FROM tb_app_topic_praise WHERE topic_id = topic.topic_id) as praiseNum, " +

            " usr.`user_name` as userName," +
            " usr.`u_avatar` as userAvatar," +
            " usr.`u_nickname` as nickname," +
            "\tusr.`user_borthday` AS userBorthday," +
            " usr.`u_sex` as sex," +

            " theme.theme_icon AS themeIcon,  " +
            " theme.theme_code AS themeCode,  " +
            " theme.theme_txt AS topicTheme" +

            " FROM tb_app_topic topic, tb_comm_user usr, tb_app_topic_theme theme" +
            " WHERE topic.`account` = ${account} AND usr.`u_account` = topic.`account` AND topic.`topic_theme` = theme.`theme_code`" +
            " ORDER BY topic.`create_time` DESC")
    IPage<TopicAo> getTopicFromUserPageList(IPage page, @Param("account") Long account);

    @Select("SELECT " +

            "topic.id as id, " +
            "topic.topic_id as topicId, " +
            "topic.account as account, " +
            "topic.topic_title as topicTitle, " +
            "topic.topic_content as topicContent, " +
            "topic.loc_lat as locLat, " +
            "topic.loc_lng as locLng, " +
            "topic.loc_address as locAddress, " +
            "topic.create_time as createTime, " +
            "topic.update_time as updateTime, " +
            "topic.topic_img as topicImg, " +
            "topic.topic_cover as topicCover, " +

            "(SELECT SUM(praise_num) FROM tb_app_topic_praise WHERE topic_id = topic.topic_id) as praiseNum, " +

            " usr.`user_name` as userName," +
            " usr.`u_avatar` as userAvatar," +
            " usr.`u_nickname` as nickname," +
            "\tusr.`user_borthday` AS userBorthday," +
            " usr.`u_sex` as sex," +

            " theme.theme_icon AS themeIcon,  " +
            " theme.theme_code AS themeCode,  " +
            "theme.theme_txt AS topicTheme" +

            " FROM tb_app_topic topic, tb_comm_user usr, tb_app_topic_theme theme" +
            " WHERE " +
            " (topic.`account` IN (SELECT f.liked_account FROM tb_comm_friends f WHERE f.`liking_account` = ${account}) OR topic.`account` = ${account}) " +
            " AND usr.`u_account` = topic.`account` AND topic.`topic_theme` = theme.`theme_code`" +
            " ORDER BY topic.`update_time` DESC")
    IPage<TopicAo> getTopicFromILikeUserPageList(IPage page, @Param("account") Long account);

    @Select("SELECT \n" +
            "\ttopic.id AS id, \n" +
            "\ttopic.topic_id AS topicId, \n" +
            "\ttopic.account AS account, \n" +
            "\ttopic.topic_title as topicTitle, " +
            "\ttopic.topic_content AS topicContent, \n" +
            "\ttopic.loc_lat AS locLat, \n" +
            "\ttopic.loc_lng AS locLng, \n" +
            "\ttopic.loc_address AS locAddress, \n" +
            "\ttopic.create_time AS createTime, \n" +
            "\ttopic.update_time AS updateTime, \n" +
            "\ttopic.topic_img as topicImg, \n" +
            "\ttopic.topic_cover as topicCover, \n" +

            "\t(SELECT SUM(praise_num) FROM tb_app_topic_praise WHERE topic_id = topic.topic_id) as praiseNum,\n" +

            "\tusr.`user_name` AS userName, \n" +
            "\tusr.`u_avatar` AS userAvatar, \n" +
            "\tusr.`u_nickname` AS nickname, \n" +
            "\tusr.`user_borthday` AS userBorthday," +
            "\tusr.`u_sex` AS sex,\n" +

            " theme.theme_icon AS themeIcon,  " +
            " theme.theme_code AS themeCode,  " +
            "\ttheme.theme_txt AS topicTheme \n" +

            "\tFROM tb_app_topic topic, tb_comm_user usr, tb_app_topic_theme theme \n" +

            "\tWHERE  usr.`u_account` = topic.`account` AND topic.`topic_theme` = theme.`theme_code` \n" +

            "\tORDER BY topic.`update_time` DESC")
    IPage<TopicAo> getNewTopicPageList(IPage page);

    @Select("SELECT topic.id AS id, \n" +
            "\ttopic.topic_id AS topicId, \n" +
            "\ttopic.account AS account, \n" +
            "\ttopic.topic_title as topicTitle, " +
            "\ttopic.topic_content AS topicContent, \n" +
            "\ttopic.loc_lat AS locLat, \n" +
            "\ttopic.loc_lng AS locLng, \n" +
            "\ttopic.loc_address AS locAddress, \n" +
            "\ttopic.create_time AS createTime, \n" +
            "\ttopic.update_time AS updateTime, \n" +
            "\ttopic.topic_img AS topicImg, \n" +
            "\ttopic.topic_cover as topicCover, " +

            "\t(SELECT SUM(praise_num) FROM tb_app_topic_praise WHERE topic_id = topic.topic_id) as praiseNum,\n" +

            "\tusr.`user_name` AS userName, \n" +
            "\tusr.`u_avatar` AS userAvatar, \n" +
            "\tusr.`u_nickname` AS nickname, \n" +
            "\tusr.`user_borthday` AS userBorthday," +
            "\tusr.`u_sex` AS sex,\n" +

            " theme.theme_icon AS themeIcon,  " +
            " theme.theme_code AS themeCode,  " +
            "\ttheme.theme_txt AS topicTheme \n" +

            "FROM tb_app_topic topic, tb_comm_user usr, tb_app_topic_theme theme \n" +

            "WHERE topic.`topic_theme` = '${themeCode}' AND usr.`u_account` = topic.`account` AND topic.`topic_theme` = theme.`theme_code` \n" +

            "ORDER BY topic.`update_time` DESC")
    IPage<TopicAo> getTopicByThemePageList(Page<TopicAo> pageVo, @Param("themeCode") String themeCode);


    @Select("SELECT " +

            "topic.id as id, " +
            "topic.topic_id as topicId, " +
            "topic.account as account, " +
            "topic.topic_title as topicTitle, " +
            "topic.topic_content as topicContent, " +
            "topic.loc_lat as locLat, " +
            "topic.loc_lng as locLng, " +
            "topic.loc_address as locAddress, " +
            "topic.create_time as createTime, " +
            "topic.update_time as updateTime, " +
            "topic.topic_img as topicImg, " +
            "topic.topic_cover as topicCover, " +

            "(SELECT SUM(praise_num) FROM tb_app_topic_praise WHERE topic_id = topic.topic_id) as praiseNum, " +

            " usr.`user_name` as userName," +
            " usr.`u_avatar` as userAvatar," +
            " usr.`u_nickname` as nickname," +
            "\tusr.`user_borthday` AS userBorthday," +
            " usr.`u_sex` as sex," +

            " theme.theme_icon AS themeIcon,  " +
            " theme.theme_code AS themeCode,  " +
            "theme.theme_txt AS topicTheme" +

            " FROM tb_app_topic topic, tb_comm_user usr, tb_app_topic_theme theme" +
            " WHERE topic.`topic_id` IN (SELECT topic_id FROM tb_app_topic_praise WHERE account = '${targetAccount}') AND usr.`u_account` = topic.`account` AND topic.`topic_theme` = theme.`theme_code`" +
            " ORDER BY topic.`create_time` DESC")
    IPage<TopicAo> getTopicWhereUserPraisePageList(Page<TopicAo> pageVo, @Param("targetAccount") String targetAccount);

    @Select("SELECT " +
            "topic.id as id, " +
            "topic.topic_id as topicId, " +
            "topic.account as account, " +
            "topic.topic_title as topicTitle, " +
            "topic.topic_content as topicContent, " +
            "topic.loc_lat as locLat, " +
            "topic.loc_lng as locLng, " +
            "topic.loc_address as locAddress, " +
            "topic.create_time as createTime, " +
            "topic.update_time as updateTime, " +
            "topic.topic_img as topicImg, " +
            "topic.topic_cover as topicCover, " +

            "(SELECT SUM(praise_num) FROM tb_app_topic_praise WHERE topic_id = topic.topic_id) as praiseNum, " +

            " usr.`user_name` as userName," +
            " usr.`u_avatar` as userAvatar," +
            " usr.`u_nickname` as nickname," +
            "\tusr.`user_borthday` AS userBorthday," +
            " usr.`u_sex` as sex," +

            " theme.theme_icon AS themeIcon,  " +
            " theme.theme_code AS themeCode,  " +
            "theme.theme_txt AS topicTheme" +

            " FROM tb_app_topic topic, tb_comm_user usr, tb_app_topic_theme theme" +
            " WHERE" +
            " topic.`topic_id` IN (SELECT topic_id FROM tb_app_topic WHERE topic_content LIKE '%${keyword}%' OR topic_title LIKE '%${keyword}%') " +

            " AND usr.`u_account` = topic.`account` AND topic.`topic_theme` = theme.`theme_code`" +
            " ORDER BY topic.`create_time` DESC")
    List<TopicAo> findTopicContent(@Param("keyword") String keyword);


    @Select("SELECT " +

            "topic.id as id, " +
            "topic.topic_id as topicId, " +
            "topic.account as account, " +
            "topic.topic_title as topicTitle, " +
            "topic.topic_content as topicContent, " +
            "topic.loc_lat as locLat, " +
            "topic.loc_lng as locLng, " +
            "topic.loc_address as locAddress, " +
            "topic.create_time as createTime, " +
            "topic.update_time as updateTime, " +
            "topic.topic_img as topicImg, " +
            "topic.topic_cover as topicCover, " +

            "(SELECT SUM(praise_num) FROM tb_app_topic_praise WHERE topic_id = topic.topic_id) as praiseNum, " +

            " usr.`user_name` as userName," +
            " usr.`u_avatar` as userAvatar," +
            " usr.`u_nickname` as nickname," +
            "\tusr.`user_borthday` AS userBorthday," +
            " usr.`u_sex` as sex," +

            " theme.theme_icon AS themeIcon,  " +
            " theme.theme_code AS themeCode,  " +
            "theme.theme_txt AS topicTheme" +

            " FROM tb_app_topic topic, tb_comm_user usr, tb_app_topic_theme theme" +
            " WHERE topic.topic_id = '${topicId}' " +
            " AND usr.`u_account` = topic.`account` AND topic.`topic_theme` = theme.`theme_code`")
    TopicAo getTopicInfo(@Param("topicId") String topicId);
}
