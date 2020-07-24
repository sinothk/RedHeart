package com.sinothk.redheart.domain;

import java.util.HashMap;

public class KeyValue {
    // 话题BIZ_TYPE
    public final String BIZ_TYPE_TOPIC = "BIZ_TYPE_TOPIC";

    // APK
    public final String BIZ_TYPE_APK = "BIZ_TYPE_APK";

    // 视频
    public final String BIZ_TYPE_VIDEO = "BIZ_TYPE_VIDEO";

    /**
     * 话题主题分类
     */
    private HashMap<String, String> topicThemMap = null;
    private final String TOPIC_THEME_SEX = "TOPIC_THEME_SEX";
    private final String TOPIC_THEME_PLANT = "TOPIC_THEME_PLANT";

//    public static String getTopicValue(String key) {
//        if (topicThemMap == null) {
//            topicThemMap = getTopicThemMap();
//        }
//        return topicThemMap.get(key);
//    }
//
//    private static HashMap<String, String> getTopicThemMap() {
//        HashMap<String, String> topicThemMap = new HashMap<>();
//        topicThemMap.put(TOPIC_THEME_SEX, "两性话题");
//        topicThemMap.put(TOPIC_THEME_PLANT, "绿植养心");
////        topicThemMap.put(, "");
////        topicThemMap.put(, "");
////        topicThemMap.put(, "");
////
////        topicThemMap.put(, "");
////        topicThemMap.put(, "");
////        topicThemMap.put(, "");
////        topicThemMap.put(, "");
////        topicThemMap.put(, "");
//        return topicThemMap;
//    }
}
