package com.sinothk.redheart.domain;

import java.util.ArrayList;

public class GaoDeAreaEntity {
//    {
//        "status" :  "1",
//        "info" : "OK",
//        "infocode" :"10000",
//        "count" : "1",
//        "districts" :
//    }

    private ArrayList<AreaEntity> districts;

    public ArrayList<AreaEntity> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<AreaEntity> districts) {
        this.districts = districts;
    }
}
