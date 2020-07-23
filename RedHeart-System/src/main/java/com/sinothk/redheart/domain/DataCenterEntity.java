package com.sinothk.redheart.domain;

import io.swagger.annotations.ApiModelProperty;





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

    public String getMouthNum() {
        return mouthNum;
    }

    public void setMouthNum(String mouthNum) {
        this.mouthNum = mouthNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
