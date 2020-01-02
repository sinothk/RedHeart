package com.sinothk.jpush.pushbyjpush.im;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.message.MessageBody;
import cn.jmessage.api.message.MessageType;
import com.sinothk.jpush.pushbyjpush.FromType;
import com.sinothk.jpush.pushbyjpush.KeyValue;
import com.sinothk.jpush.pushbyjpush.TargetType;

public class JMessageHelper {
    public static String USER_ADMIN = "9999";

    public static void sendTxtMsg(String receivedAccount, String sendAccount, String msg) {
        new Thread(() -> {
            JMessageClient client = new JMessageClient(KeyValue.APP_KEY, KeyValue.MASTER_SECRET);

            MessageBody.Builder messageBodyBuilder = new MessageBody.Builder();
            messageBodyBuilder.addExtra("v1", "1111");
            messageBodyBuilder.setText(msg);
            try {
                client.sendMessage(1, TargetType.SINGLE, receivedAccount, FromType.USER, sendAccount, MessageType.TEXT, messageBodyBuilder.build());
            } catch (APIConnectionException | APIRequestException e) {
                e.printStackTrace();
            }
        }).start();
    }

//    public static void main(String[] args) {
//        sendTxtMsg("10033", "9999", "欢迎加入异趣，很高心在这里遇见您！");
//    }
}
