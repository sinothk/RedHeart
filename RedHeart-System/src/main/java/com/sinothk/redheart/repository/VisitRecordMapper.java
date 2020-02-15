package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.redheart.domain.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("visitRecordMapper")
public interface VisitRecordMapper extends BaseMapper<VisitorRecordEntity> {

    @Select("SELECT \n" +
            "\ttcv.id id, \n" +
            "\ttcv.account account, \n" +
            "\ttcv.visitor visitor, \n" +
            "\ttcv.visit_time visitTime,\n" +
            "\ttcv.visit_type visitType,  \n" +
            "\ttcv.visit_num visitNum,\n" +
            "\t \n" +
            "\ttcu.`u_nickname` nickname,\n" +
            "\ttcu.`user_name` userName,\n" +
            "\ttcu.`u_avatar` userAvatar\n" +

            "FROM \n" +
            "\ttb_comm_visitor tcv,\n" +
            "\ttb_comm_user tcu\n" +
            "\t\n" +

            "WHERE \n" +
            "\t tcv.`account` = ${account} AND tcv.visitor = tcu.`u_account` " +
            "\t ORDER BY tcv.visit_time DESC")
    IPage<VisitorRecordAO> getVisitRecordList(Page<VisitorRecordAO> pageVo, @Param("account") String account);


//    @Select("SELECT " +
//            "\tnotice.`id` as id," +
//            "\tnotice.`title` as title," +
//            "\tnotice.`content` as content," +
//            "\tnotice.`cover_path` as coverPath," +
//            "\tnotice.`create_account` as createAccount," +
//            "\tnotice.`create_time` as createTime," +
//            "\tnotice.`public_time` as publicTime," +
//            "\tusr.`u_avatar` as avatar," +
//            "\tusr.`user_name` as userName," +
//            "\tusr.`u_nickname` as nickname," +
//            "\tusr.`u_sex` as sex," +
//            "\tusr.`user_borthday` as borthday," +
//
//            "\t(SELECT COUNT(id) FROM tb_comm_notice_reader nr WHERE nr.notice_id = notice.`id`) AS readerNum" +
//
//            "\tFROM tb_comm_notice notice, tb_comm_user usr " +
//            "\tWHERE notice.`create_account` = usr.`u_account`" +
//            "\tORDER BY notice.`create_time` DESC")
//    IPage<NoticeAo> getAllNoticeList(Page<NoticeAo> pageVo, @Param("account") Long account);
//
//
//    @Select("SELECT \n" +
//            "\tusr.`id` as id,\n" +
//            "\tusr.`u_account` as account,\n" +
//            "\tusr.`u_avatar` as avatar,\n" +
//            "\tusr.`user_name` as userName,\n" +
//            "\tusr.`u_nickname` as nickname,\n" +
//            "\tusr.`u_sex` as sex,\n" +
//            "\tusr.`user_borthday` as userBorthday,\n" +
//
//            "\tnr.`read_time` as readTime\n" +
//
//            "FROM tb_comm_notice_reader nr, tb_comm_user usr \n" +
//            "WHERE  nr.notice_id = ${noticeId} AND  nr.`reader_account` = usr.`u_account`\n" +
//            "ORDER BY nr.`read_time` ASC")
//    IPage<NoticeReaderVo> getNoticeReaderList(Page<NoticeReaderVo> pageVo, @Param("noticeId") Long noticeId);

}
