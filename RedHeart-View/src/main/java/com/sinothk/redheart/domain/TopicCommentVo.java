package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ApiModel("话题评论返回实体")
@Data
@ToString
public class TopicCommentVo {

//        "SELECT \n" +
//        "\tcomm.`id` AS id, " +
//        "\tcomm.`send_account` AS sendAccount, " +
//        "\tcomm.`receive_account` AS receiveAccount," +
//        "\tcomm.`com_content` AS comContent," +
//        "\tcomm.`topic_id` AS topicId," +
//        "\tcomm.`create_time` AS createTime," +
//
//        "\tusr.`user_name` AS userName," +
//        "\tusr.`u_avatar` AS userAvatar," +
//        "\tusr.`u_nickname` AS nickname," +
//        "\tusr.`u_sex` AS sex" +
//
//        "\tFROM tb_app_topic_comment comm, tb_comm_user usr " +
//        "\tWHERE comm.`topic_id` = ${topicId} AND comm.`send_account` = usr.`u_account`" +
//        "\tORDER BY comm.`create_time` DESC")

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("话题Id")
    private String topicId;

    @ApiModelProperty("发送人账号")
    private Long sendAccount;

    @ApiModelProperty("接收人账号")
    private Long receiveAccount;

    @ApiModelProperty("评论内容")
    private String comContent;

    @ApiModelProperty("发布时间")
    private Date createTime;

    @ApiModelProperty("评论人姓名")
    private String userName;

    @ApiModelProperty("评论人头像")
    private String userAvatar;

    @ApiModelProperty("评论人昵称")
    private String nickname;

    @ApiModelProperty("评论人昵称")
    private Integer sex;
}
