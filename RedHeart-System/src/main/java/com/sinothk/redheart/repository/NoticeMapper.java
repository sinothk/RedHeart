package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.redheart.domain.LoginRecordEntity;
import com.sinothk.redheart.domain.NoticeEntity;
import com.sinothk.redheart.domain.NoticeVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository("noticeMapper")
public interface NoticeMapper extends BaseMapper<NoticeEntity> {


    @Select("SELECT " +
            "\tnotice.`id` as id," +
            "\tnotice.`title` as title," +
            "\tnotice.`content` as content," +
            "\tnotice.`cover_path` as coverPath," +
            "\tnotice.`create_account` as createAccount," +
            "\tnotice.`create_time` as createTime," +
            "\tnotice.`public_time` as publicTime," +
            "\tusr.`u_avatar` as avatar," +
            "\tusr.`user_name` as userName," +
            "\tusr.`u_nickname` as nickname," +
            "\tusr.`u_sex` as sex," +
            "\tusr.`user_borthday` as borthday," +

            "\t(SELECT COUNT(id) FROM tb_comm_notice_reader nr WHERE nr.notice_id = notice.`id`) AS readerNum"+

            "\tFROM tb_comm_notice notice, tb_comm_user usr " +
            "\tWHERE notice.`create_account` = usr.`u_account`" +
            "\tORDER BY notice.`create_time` DESC")
    IPage<NoticeVo> getAllNoticeList(Page<NoticeVo> pageVo, @Param("account") Long account);

//    @Select("select keep_account from tb_comm_init_account")
//    Set<Long> getSysKeepAccountSet();
//
//    @Select("select u_account from tb_comm_user ORDER BY id ASC")
//    Set<Long> getUserAccountSet();


}
