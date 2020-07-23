package com.sinothk.redheart.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel("话题点赞实体")


@TableName(value = "tb_app_topic_praise")
public class TopicPraiseEntity {
//    Field       Type         Collation        Null    Key     Default  Extra           Privileges                       Comment
//----------  -----------  ---------------  ------  ------  -------  --------------  -------------------------------  -----------------
//    id          bigint(20)   (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references
//    account     bigint(20)   (NULL)           NO              (NULL)                   select,insert,update,references  点赞人账号
//    topic_id    varchar(32)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  话题Id
//    praise_num  int(11)      (NULL)           YES             (NULL)                   select,insert,update,references  点赞次数

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("点赞人账号")
    @TableField(value = "account")
    private Long account;

    @ApiModelProperty("点赞话题ID")
    @TableField(value = "topic_id")
    private String topicId;

    @ApiModelProperty("点赞次数")
    @TableField(value = "praise_num")
    private Integer praiseNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }
}
