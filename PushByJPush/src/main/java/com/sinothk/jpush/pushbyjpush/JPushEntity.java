package com.sinothk.jpush.pushbyjpush;

import com.alibaba.fastjson.JSON;





public class JPushEntity {

    public static int MSG_TYPE_RELATION = 0; // 关注事件提醒
    public static int MSG_TYPE_PRAISE = 1;  // 点赞事件提醒
    public static int MSG_TYPE_NOTICE = 2;  // 系统通知提醒
    public static int MSG_TYPE_COMMENT = 3; // 话题评论提醒
    public static int MSG_TYPE_VERSION = 5; // 新版本提醒
    public static int MSG_TYPE_TOPIC_NEW = 6; // 新增话题

    private int msgType;
    private String msgData;

    public static String createData(int msgType, Object dataObj) {
        JPushEntity jpushEntity = new JPushEntity();
        jpushEntity.setMsgType(msgType);
        jpushEntity.setMsgData(JSON.toJSONString(dataObj));
        return JSON.toJSONString(jpushEntity);
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getMsgData() {
        return msgData;
    }

    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }
}
