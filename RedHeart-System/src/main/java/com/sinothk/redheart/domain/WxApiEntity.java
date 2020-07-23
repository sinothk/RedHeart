package com.sinothk.redheart.domain;






public class WxApiEntity {
//    {
//        "session_key": "6sVdu/IQzJz5FuFcGy8IKg==",
//            "openid": "om5xp5CeqZdfRKAzUG-MliUrRM_k"
//    }
    private String session_key;
    private String openid;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
