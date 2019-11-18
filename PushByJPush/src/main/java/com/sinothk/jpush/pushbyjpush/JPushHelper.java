package com.sinothk.jpush.pushbyjpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JPushHelper {

    private static final String MASTER_SECRET = "783b80b7be1590bcff378ff8";
    private static final String APP_KEY = "3e3250f7c049c0d226fcfbba";

    private static final Logger LOG = LoggerFactory.getLogger(JPushHelper.class);

//    public static void main(String[] args) {
//    }


    /**
     * 推送给所有用户
     *
     * @param title
     * @param subTitle
     * @param data
     */
    public static void pushForAll(String title, String subTitle, String data) {
        pushBase(Audience.all(), title, subTitle, data);
    }

    /**
     * 推送给特定用户: 通过注册Id
     *
     * @param aliasList
     * @param title
     * @param subTitle
     * @param data
     */
    public static void pushByRegId(ArrayList<String> aliasList, String title, String subTitle, String data) {
        pushBase(Audience.registrationId(aliasList), title, subTitle, data);
    }

    /**
     * 推送给特定用户: 通过别名
     *
     * @param aliasList
     * @param title
     * @param subTitle
     * @param data
     */
    public static void pushByAlias(ArrayList<String> aliasList, String title, String subTitle, String data) {
        pushBase(Audience.alias(aliasList), title, subTitle, data);
    }

    private static void pushBase(Audience audience, String title, String subTitle, String data) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_android_and_ios(audience, title, subTitle, data);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    private static PushPayload buildPushObject_android_and_ios(Audience audience, String title, String subTitle, String data) {

        Map<String, String> extras = new HashMap<>();
        extras.put("test", "https://community.jiguang.cn/push");
        extras.put("data", data);

        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())

//                .setAudience(Audience.all())
//                .setAudience(Audience.alias(new ArrayList<>()))
//                .setAudience(Audience.registrationId(new ArrayList<>()))

                .setAudience(audience)

                .setNotification(Notification.newBuilder()
                        .setAlert(subTitle)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title)
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();
    }

    // ========================================================================================

    /**
     * 发送透传：所有用户
     *
     * @param data
     */
    public static void pushMsgForAll(String data) {
        pushMsgBase(Audience.all(), data);
    }

    /**
     * 发送透传：指定别名用户
     *
     * @param aliasList
     * @param data
     */
    public static void pushMsgByAlias(ArrayList<String> aliasList, String data) {
        pushMsgBase(Audience.alias(aliasList), data);
    }

    /**
     * 发送透传：指定ResId用户
     *
     * @param regIdList
     * @param data
     */
    public static void pushMsgByRegId(ArrayList<String> regIdList, String data) {
        pushMsgBase(Audience.registrationId(regIdList), data);
    }

    private static void pushMsgBase(Audience audience, String data) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushMsgObject(audience, data);

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    private static PushPayload buildPushMsgObject(Audience audience, String data) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(audience)
                .setMessage(Message.newBuilder()
                        .setMsgContent(data)
//                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
}
