package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;





public class ThemeOtherInfoAo {

    @ApiModelProperty("主题下的用户数")
    private Integer themeMemberNum;

    @ApiModelProperty("主题下的话题")
    private Integer themeTopicNum;

    @ApiModelProperty("用户和主题关系：0、未关注；1、关注")
    private Boolean themeUserRela;

    public Integer getThemeMemberNum() {
        return themeMemberNum;
    }

    public void setThemeMemberNum(Integer themeMemberNum) {
        this.themeMemberNum = themeMemberNum;
    }

    public Integer getThemeTopicNum() {
        return themeTopicNum;
    }

    public void setThemeTopicNum(Integer themeTopicNum) {
        this.themeTopicNum = themeTopicNum;
    }

    public Boolean getThemeUserRela() {
        return themeUserRela;
    }

    public void setThemeUserRela(Boolean themeUserRela) {
        this.themeUserRela = themeUserRela;
    }
}
