package com.sinothk.redheart.utils.wx;

import java.util.HashMap;
import java.util.Map;

public class WxUtil {

    public static String wx_appid = "wxb44d25f9d54a8beb";
    public static String wx_secret = "e55f8024fefc4aeece8722719084eb38";

    /**
     * 根据code获得OpenId
     *
     * @param code
     * @return
     */
    public static String getOpenID(String code) {
        try {
            // 组装参数*****
            Map<String, String> urlData = new HashMap<>();
            urlData.put("appid", WxUtil.wx_appid);//小程序id
            urlData.put("secret", WxUtil.wx_secret);//小程序key
            urlData.put("grant_type", "authorization_code");//固定值这样写就行
            urlData.put("js_code", code);//小程序传过来的code

            String code2OpenidUrl = "https://api.weixin.qq.com/sns/jscode2session";
            return new HttpsClientUtil().doGet(code2OpenidUrl, urlData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
