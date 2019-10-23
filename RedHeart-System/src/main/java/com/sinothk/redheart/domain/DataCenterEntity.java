package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DataCenterEntity implements Comparable<DataCenterEntity> {

    public DataCenterEntity() {
    }

    public DataCenterEntity(String mouthNum, Integer total) {
        this.mouthNum = mouthNum;
        this.total = total;
    }

    @ApiModelProperty(value = "月份")
    private String mouthNum;

    @ApiModelProperty(value = "统计数量")
    private Integer total;

    @Override
    public int compareTo(DataCenterEntity obj) {
        return Integer.valueOf(this.getMouthNum()) - Integer.valueOf(obj.getMouthNum());
    }
}
