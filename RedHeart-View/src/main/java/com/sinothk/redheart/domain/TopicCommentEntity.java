package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@ApiModel("话题评论实体")


@TableName(value = "tb_app_topic_comment")
public class TopicCommentEntity {

//    CREATE TABLE `tb_app_topic_comment` (
//            `id` bigint(20) NOT NULL AUTO_INCREMENT,
//  `topic_id` varchar(32) DEFAULT NULL,
//  `com_content` varchar(1024) DEFAULT NULL,
//  `create_time` datetime DEFAULT NULL,
//            `com_account` bigint(20) DEFAULT NULL,
//    PRIMARY KEY (`id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty("话题Id")
    @TableField("topic_id")
    private String topicId;

    @ApiModelProperty("接收人账号")
    @TableField("receive_account")
    private Long receiveAccount;

    @ApiModelProperty("评论内容")
    @TableField("com_content")
    private String comContent;

    @ApiModelProperty("发送人账号")
    @TableField("send_account")
    private Long sendAccount;

    @ApiModelProperty("发布时间")
    @TableField("create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Long getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(Long receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Long getSendAccount() {
        return sendAccount;
    }

    public void setSendAccount(Long sendAccount) {
        this.sendAccount = sendAccount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
