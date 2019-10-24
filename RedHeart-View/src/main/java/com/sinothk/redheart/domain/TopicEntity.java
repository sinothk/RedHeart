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

@ApiModel("话题实体")
@Data
@ToString
@TableName(value = "tb_app_topic")
public class TopicEntity {
//    /*列信息*/-----------
//
//    Field          Type           Collation        Null    Key     Default  Extra           Privileges                       Comment
//-------------  -------------  ---------------  ------  ------  -------  --------------  -------------------------------  -----------------
//    id             int(11)        (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references
//    account        bigint(20)     (NULL)           NO              (NULL)                   select,insert,update,references  发布人账号
//    topic_content  varchar(2048)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  发布内容
//    create_time    datetime       (NULL)           YES             (NULL)                   select,insert,update,references  发布时间
//    loc_lat        double         (NULL)           YES             (NULL)                   select,insert,update,references  纬度
//    loc_lng        double         (NULL)           YES             (NULL)                   select,insert,update,references  经度
//    loc_address    varchar(512)   utf8_general_ci  YES             (NULL)                   select,insert,update,references  位置
//    biz_type         varchar(64)    utf8_general_ci  YES             (NULL)                   select,insert,update,references  业务类型

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @ApiModelProperty("话题Id")
    @TableField("topic_id")
    private String topicId;

    @ApiModelProperty("发布人账号")
    @TableField("account")
    private Long account;

    @ApiModelProperty("发布内容")
    @TableField("topic_content")
    private String topicContent;

    @ApiModelProperty("发布纬度")
    @TableField("loc_lat")
    private Double locLat;

    @ApiModelProperty("发布经度")
    @TableField("loc_lng")
    private Double locLng;

    @ApiModelProperty("发布地址")
    @TableField("loc_address")
    private String locAddress;

    @ApiModelProperty("发布时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("发布时间")
    @TableField("update_time")
    private Date updateTime;
}
