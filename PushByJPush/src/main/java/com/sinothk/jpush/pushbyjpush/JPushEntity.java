package com.sinothk.jpush.pushbyjpush;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JPushEntity {

    public static int MSG_TYPE_RELATION = 0; // 关注事件提醒
    public static int MSG_TYPE_PRAISE = 1;  // 点赞事件提醒
    public static int MSG_TYPE_NOTICE = 2;  // 系统通知提醒
    public static int MSG_TYPE_COMMENT = 3; // 话题评论提醒
    public static int MSG_TYPE_VERSION = 5; // 新版本提醒

    private int msgType;
    private String msgData;

    public static String createData(int msgType, Object dataObj) {
        JPushEntity jpushEntity = new JPushEntity();
        jpushEntity.setMsgType(msgType);
        jpushEntity.setMsgData(JSON.toJSONString(dataObj));
        return JSON.toJSONString(jpushEntity);
    }
}
