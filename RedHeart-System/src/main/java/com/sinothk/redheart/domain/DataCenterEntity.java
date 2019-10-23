package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DataCenterEntity {

    @ApiModelProperty(value = "月份")
    private String mouthNum;

    @ApiModelProperty(value = "统计数量")
    private Integer total;
}
