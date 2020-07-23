package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@Api(value = "通知阅读实体")


@TableName("tb_comm_notice_reader")
public class NoticeReaderEntity {

//CREATE TABLE `tb_comm_notice_reader` (
//            `id` bigint(20) NOT NULL AUTO_INCREMENT,
//  `notice_id` bigint(20) DEFAULT NULL,
//  `user_account` bigint(20) DEFAULT NULL,
//  `read_time` datetime DEFAULT NULL,
//    PRIMARY KEY (`id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty("发布人账号")
    @TableField(value = "notice_id")
    private Long noticeId;

    @ApiModelProperty("阅读人账号")
    @TableField(value = "reader_account")
    private Long readerAccount;

    @ApiModelProperty("发布时间")
    @TableField(value = "read_time")
    private Date readTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getReaderAccount() {
        return readerAccount;
    }

    public void setReaderAccount(Long readerAccount) {
        this.readerAccount = readerAccount;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }
}
