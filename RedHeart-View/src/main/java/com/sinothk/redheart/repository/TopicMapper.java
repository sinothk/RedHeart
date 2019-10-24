package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinothk.redheart.domain.TopicAo;
import com.sinothk.redheart.domain.TopicEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("topicMapper")
public interface TopicMapper extends BaseMapper<TopicEntity> {



    @Select("SELECT " +

            "topic.id as id, " +
            "topic.topic_id as topicId, " +
            "topic.account as account, " +
            "topic.topic_content as topicContent, " +
            "topic.loc_lat as locLat, " +
            "topic.loc_lng as locLng, " +
            "topic.loc_address as locAddress, " +
            "topic.create_time as createTime, " +
            "topic.update_time as updateTime, " +

            " usr.`user_name` as userName," +
            " usr.`u_avatar` as userAvatar," +
            " usr.`u_nickname` as nickname," +
            " usr.`u_sex` as sex," +
            "theme.theme_txt AS topicTheme" +

            " FROM tb_app_topic topic, tb_comm_user usr, tb_app_topic_theme theme" +
            " WHERE topic.`account` = ${account} AND usr.`u_account` = topic.`account` AND topic.`topic_theme` = theme.`theme_code`" +
            " ORDER BY topic.`create_time` DESC")
    IPage<TopicAo> getTopicFromMePageList(IPage page, @Param("account") Long account);
}
