package com.sinothk.redheart.domain;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class GaoDeAreaEntity {
//    {
//        "status" :  "1",
//        "info" : "OK",
//        "infocode" :"10000",
//        "count" : "1",
//        "districts" :
//    }

    private ArrayList<AreaEntity> districts;


}
