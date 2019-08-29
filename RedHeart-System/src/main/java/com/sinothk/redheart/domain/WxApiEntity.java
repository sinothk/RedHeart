package com.sinothk.redheart.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WxApiEntity {
//    {
//        "session_key": "6sVdu/IQzJz5FuFcGy8IKg==",
//            "openid": "om5xp5CeqZdfRKAzUG-MliUrRM_k"
//    }
    private String session_key;
    private String openid;


}
