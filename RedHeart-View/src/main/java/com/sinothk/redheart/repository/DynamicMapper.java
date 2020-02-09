package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
import com.sinothk.redheart.domain.TopicThemeAo;
import com.sinothk.redheart.domain.TopicThemeEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dynamicMapper")
public interface DynamicMapper extends BaseMapper<TopicEntity> {

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
            "(SELECT COUNT(id) FROM tb_app_topic_comment WHERE topic_id = topic.topic_id) AS commentNum, " +

            " usr.`user_name` as userName," +
            " usr.`u_avatar` as userAvatar," +
            " usr.`u_nickname` as nickname," +
            "\tusr.`user_borthday` AS userBorthday," +
            " usr.`u_sex` as sex" +

            " FROM tb_app_topic topic, tb_comm_user usr" +

            " WHERE topic.`account` = ${account} AND usr.`u_account` = topic.`account` " +
            " ORDER BY topic.`create_time` DESC")
    IPage<TopicAo> findDynamicByAccount(IPage page, @Param("account") String account);

}
