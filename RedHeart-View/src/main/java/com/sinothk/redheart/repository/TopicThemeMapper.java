package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.domain.TopicThemeAo;
import com.sinothk.redheart.domain.TopicThemeEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("topicThemeMapper")
public interface TopicThemeMapper extends BaseMapper<TopicThemeEntity> {

//    @Select("SELECT " +
//
//            "topic.id as id, " +
//            "topic.topic_id as topicId, " +
//            "topic.account as account, " +
//            "topic.topic_theme as topicTheme, " +
//            "topic.topic_content as topicContent, " +
//            "topic.loc_lat as locLat, " +
//            "topic.loc_lng as locLng, " +
//            "topic.loc_address as locAddress, " +
//            "topic.create_time as createTime, " +
//            "topic.update_time as updateTime, " +
//
//            " usr.`user_name` as userName," +
//            " usr.`u_avatar` as userAvatar," +
//            " usr.`u_nickname` as nickname," +
//            " usr.`u_sex` as sex" +
//
//            " FROM tb_app_topic topic, tb_comm_user usr" +
//            " WHERE topic.`account` = ${account} AND usr.`u_account` = topic.`account`" +
//            " ORDER BY topic.`create_time` DESC")
//    IPage<TopicAo> getTopicFromMePageList(IPage page, @Param("account") Long account);

    @Select("SELECT " +
            "\ttopicTheme.id AS id," +
            "\ttopicTheme.theme_code AS themeCode," +
            "\ttopicTheme.theme_txt AS themeTxt," +
            "\ttopicTheme.theme_icon AS themeIcon," +
            "\ttopicTheme.remark AS remark," +
            "\ttopicTheme.sort_num AS sortNum," +
            "\ttopicTheme.sex_type," +

            "\t( SELECT COUNT(topic.id) FROM tb_app_topic topic WHERE topic.topic_theme = topicTheme.theme_code ) AS topicNum," +

            "\t( SELECT COUNT(themeUser.id) FROM tb_app_topic_theme_user themeUser WHERE themeUser.theme_code = topicTheme.`theme_code` AND themeUser.account = ${account} ) AS relNum" +

            "\tFROM tb_app_topic_theme topicTheme " +

            "\tWHERE topicTheme.theme_status = 0 AND (topicTheme.sex_type = ${sexType} OR topicTheme.sex_type = -1)" +

            "\tORDER BY sortNum ASC")
        //relNum DESC,
    List<TopicThemeAo> getAllTopicThemeList(@Param("account") Long account, @Param("sexType") int sex);

}
