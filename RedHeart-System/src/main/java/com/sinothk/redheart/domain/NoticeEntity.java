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

@Api(value = "通知实体")
@Data
@ToString
@TableName("tb_comm_notice")
public class NoticeEntity {

//   CREATE TABLE `tb_comm_notice` (
//            `id` bigint(20) NOT NULL AUTO_INCREMENT,
//  `title` varchar(100) DEFAULT NULL,
//  `content` varchar(1024) DEFAULT NULL,
//  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
//            `public_time` datetime DEFAULT NULL COMMENT '发布时间',
//            `create_account` bigint(20) DEFAULT NULL,
//  `cover_path` varchar(512) DEFAULT NULL,
//    PRIMARY KEY (`id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty("标题")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty("内容")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty("封面")
    @TableField(value = "cover_path")
    private String coverPath;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    @ApiModelProperty("发布时间")
    @TableField(value = "public_time")
    private Date publicTime;

    @ApiModelProperty("发布人账号")
    @TableField(value = "create_account")
    private Long createAccount;
}
