package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TopicVo extends TopicEntity{

    @ApiModelProperty("发布人账号")
    private Long account;
}
