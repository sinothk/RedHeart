package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.redheart.domain.TopicCommentEntity;
import com.sinothk.redheart.domain.TopicCommentVo;
import com.sinothk.redheart.domain.TopicPraiseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("topicPraiseMapper")
public interface TopicPraiseMapper extends BaseMapper<TopicPraiseEntity> {


//    @Select("SELECT \n" +
//            "\tcomm.`id` AS id, " +
//            "\tcomm.`send_account` AS sendAccount, " +
//            "\tcomm.`receive_account` AS receiveAccount," +
//            "\tcomm.`com_content` AS comContent," +
//            "\tcomm.`topic_id` AS topicId," +
//            "\tcomm.`create_time` AS createTime," +
//
//            "\tusr.`user_name` AS userName," +
//            "\tusr.`u_avatar` AS userAvatar," +
//            "\tusr.`u_nickname` AS nickname," +
//            "\tusr.`user_borthday` AS userBorthday," +
//            "\tusr.`u_sex` AS sex" +
//
//            "\tFROM tb_app_topic_comment comm, tb_comm_user usr " +
//            "\tWHERE comm.`topic_id` = '${topicId}' AND comm.`send_account` = usr.`u_account`" +
//            "\tORDER BY comm.`create_time` DESC")
//    IPage<TopicCommentVo> getTopicCommentPageList(Page<TopicCommentVo> pageVo, @Param("topicId") String topicId);

    @Select("SELECT SUM(praise_num) FROM tb_app_topic_praise WHERE topic_id = '${topicId}'")
    int selectTopicPraiseNum( @Param("topicId") String topicId);
}
