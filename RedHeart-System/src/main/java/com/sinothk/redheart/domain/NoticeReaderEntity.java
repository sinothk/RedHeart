package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Api(value = "通知阅读实体")
@Data
@ToString
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
    @TableField(value = "create_account")
    private Long createAccount;

    @ApiModelProperty("发布时间")
    @TableField(value = "public_time")
    private Date publicTime;
}
