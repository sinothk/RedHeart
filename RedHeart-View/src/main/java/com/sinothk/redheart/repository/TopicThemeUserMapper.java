package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.TopicThemeAo;
import com.sinothk.redheart.domain.TopicThemeUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("topicThemeUserMapper")
public interface TopicThemeUserMapper extends BaseMapper<TopicThemeUserEntity> {

    @Select("SELECT " +

            " tatt.id as id, " +
            " tatt.theme_code as themeCode, " +
            " tatt.theme_txt as themeTxt, " +
            " tatt.theme_icon as themeIcon, " +
            " tatt.remark as remark, " +
            " tatt.sort_num as sortNum," +
            " tattu.account as account, " +
            " tattu.create_time as createTime" +

            " FROM tb_app_topic_theme tatt, tb_app_topic_theme_user tattu " +
            " WHERE tatt.theme_code = tattu.theme_code AND tattu.account = ${account} ORDER BY tattu.create_time DESC")
    List<TopicThemeAo> getMyTopicThemeList(@Param("account") Long account);
}
