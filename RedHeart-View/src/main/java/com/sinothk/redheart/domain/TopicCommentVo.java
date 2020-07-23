package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import java.util.Date;

@ApiModel("话题评论返回实体")


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

    @ApiModelProperty("评论人性别")
    private Integer sex;

    @ApiModelProperty("发布人生日")
    private Date userBorthday;

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

    public Long getSendAccount() {
        return sendAccount;
    }

    public void setSendAccount(Long sendAccount) {
        this.sendAccount = sendAccount;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getUserBorthday() {
        return userBorthday;
    }

    public void setUserBorthday(Date userBorthday) {
        this.userBorthday = userBorthday;
    }
}
