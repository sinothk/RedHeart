package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ThemeOtherInfoAo {

    @ApiModelProperty("主题下的用户数")
    private Integer themeMemberNum;

    @ApiModelProperty("主题下的话题")
    private Integer themeTopicNum;

    @ApiModelProperty("用户和主题关系：0、未关注；1、关注")
    private Boolean themeUserRela;

}
