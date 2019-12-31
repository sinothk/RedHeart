package com.sinothk.redheart.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserVo {

    private Long account;
    private String email;
    private String userPwd;
    //
    private Double loginLat;
    private Double loginLon;

    private double lat;
    private double lng;
    //
    private String imei;
}
