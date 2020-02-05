package com.sinothk.redheart.utils;

import java.util.HashMap;
import java.util.Random;

public class SystemDefaultData {


    private static HashMap<Integer, String> yearStrMap = null;

    public static String getOneYear() {

        if (yearStrMap == null) {
            yearStrMap = new HashMap<>();

            yearStrMap.put(0, "1985-01-05");
            yearStrMap.put(1, "1986-02-06");
            yearStrMap.put(2, "1987-03-08");
            yearStrMap.put(3, "1987-04-05");
            yearStrMap.put(4, "1988-05-25");
            yearStrMap.put(5, "1989-06-15");
            yearStrMap.put(6, "1990-07-20");
            yearStrMap.put(7, "1990-08-05");
            yearStrMap.put(8, "1991-09-15");
            yearStrMap.put(9, "1992-11-15");
            yearStrMap.put(10, "1993-01-25");
            yearStrMap.put(11, "1993-10-25");
            yearStrMap.put(12, "1994-11-25");
            yearStrMap.put(13, "1994-08-25");
            yearStrMap.put(14, "1995-10-25");
            yearStrMap.put(15, "1995-11-25");
            yearStrMap.put(16, "1996-12-25");
        }

        int num = new Random().nextInt(yearStrMap.size());
        return yearStrMap.get(num);
    }

    private static Random random = null;
    private static HashMap<Integer, String> oneWordMap, twoWordMap, threeWordMap, fourWordMap, fiveWordMap;
    private static HashMap<Integer, String> nicknameMale2Map, nicknameMale3Map;

    @Deprecated
    public static String getNicknameStr(String param, String defaultVal) {

        String nickname;

        if (random == null) {
            random = new Random();
        }

        int num = random.nextInt(5) + 1;
        switch (num) {
            case 1:
                String name = get3Word();
                nickname = name + name;
                break;
            case 2:
                nickname = get2Word() + get3Word();
                break;
            case 3:
                nickname = get1Word() + get2Word() + get3Word();
                break;
            case 4:
                nickname = get1Word() + get3Word();
                break;
            case 5:
                nickname = get1Word() + get3Word();
                break;
            default:
                nickname = defaultVal;
                break;
        }

        return nickname;
    }

    private static String get1Word() {
        if (oneWordMap == null) {
            oneWordMap = new HashMap<>();

            oneWordMap.put(0, "王");
            oneWordMap.put(1, "李");
            oneWordMap.put(2, "张");
            oneWordMap.put(3, "刘");
            oneWordMap.put(4, "陈");
            oneWordMap.put(5, "杨");
            oneWordMap.put(6, "吴");
            oneWordMap.put(7, "周");
            oneWordMap.put(8, "魏");
            oneWordMap.put(9, "唐");
            oneWordMap.put(10, "楚");
        }
//        int key = random.nextInt(oneWordMap.size());
        return oneWordMap.get(random.nextInt(oneWordMap.size()));
    }

    private static String get2Word() {
        if (twoWordMap == null) {
            twoWordMap = new HashMap<>();
            twoWordMap.put(0, "小");
            twoWordMap.put(1, "阿");
            twoWordMap.put(2, "梦");
            twoWordMap.put(3, "雨");
            twoWordMap.put(4, "梦");
            twoWordMap.put(5, "若");
        }

        int key = random.nextInt(twoWordMap.size());
        return twoWordMap.get(key);
    }

    private static String get3Word() {
        if (threeWordMap == null) {
            threeWordMap = new HashMap<>();

            threeWordMap.put(0, "婷");
            threeWordMap.put(1, "义");
            threeWordMap.put(2, "亦");
            threeWordMap.put(3, "青");
            threeWordMap.put(4, "梅");
            threeWordMap.put(5, "慧");
            threeWordMap.put(6, "然");
            threeWordMap.put(7, "芯");
            threeWordMap.put(8, "笑");
            threeWordMap.put(9, "慧");
            threeWordMap.put(10, "熙");
            threeWordMap.put(11, "紫");
            threeWordMap.put(12, "慧");
            threeWordMap.put(13, "蕾");
            threeWordMap.put(14, "涵");
            threeWordMap.put(15, "雅");
            threeWordMap.put(16, "芳");
            threeWordMap.put(17, "柳");
            threeWordMap.put(18, "丹");
            threeWordMap.put(19, "瑶");
            threeWordMap.put(20, "薇");
            threeWordMap.put(21, "梦");
            threeWordMap.put(22, "菱");
            threeWordMap.put(23, "珊");
            threeWordMap.put(24, "君");
            threeWordMap.put(25, "兰");
            threeWordMap.put(26, "琴");
            threeWordMap.put(27, "文");
            threeWordMap.put(28, "禧");
            threeWordMap.put(29, "羲");
            threeWordMap.put(30, "萍");
        }

        int key = random.nextInt(threeWordMap.size());
        return threeWordMap.get(key);
    }

    /**
     * 获取默认用户名
     *
     * @param sex
     * @param defaultVal
     * @return
     */
    public static String getNickname(Integer sex, String defaultVal) {
        String nickname;

        if (random == null) {
            random = new Random();
        }

        int num = random.nextInt(5) + 1;
        switch (num) {
            case 1:

                if (sex == 0) {
                    String name = get3Word();
                    nickname = name + name;
                } else {
                    String name = getMale3Word();
                    nickname = name + name;
                }

                break;
            case 2:
                if (sex == 0) {
                    nickname = get2Word() + get3Word();
                } else {
                    nickname = getMale2Word() + getMale3Word();
                }
                break;
            case 3:
                if (sex == 0) {
                    nickname = get1Word() + get2Word() + get3Word();
                } else {
                    nickname = get1Word() + getMale2Word() + getMale3Word();
                }
                break;
            case 4:
                if (sex == 0) {
                    nickname = get1Word() + get3Word();
                } else {
                    nickname = get1Word() + getMale3Word();
                }

                break;
            case 5:
                if (sex == 0) {
                    nickname = get1Word() + get3Word();
                } else {
                    nickname = get1Word() + getMale3Word();
                }
                break;
            default:
                nickname = defaultVal;
                break;
        }

        return nickname;
    }

    private static String getMale2Word() {
        if (nicknameMale2Map == null) {
            nicknameMale2Map = new HashMap<>();
            nicknameMale2Map.put(0, "小");
            nicknameMale2Map.put(1, "阿");
            nicknameMale2Map.put(2, "大");
            nicknameMale2Map.put(3, "豪");
            nicknameMale2Map.put(4, "杰");
            nicknameMale2Map.put(5, "军");
        }

        int key = random.nextInt(nicknameMale2Map.size());
        return nicknameMale2Map.get(key);
    }

    private static String getMale3Word() {
        if (nicknameMale3Map == null) {
            nicknameMale3Map = new HashMap<>();

            nicknameMale3Map.put(0, "浩");
            nicknameMale3Map.put(1, "军");
            nicknameMale3Map.put(2, "国");
            nicknameMale3Map.put(3, "能");
            nicknameMale3Map.put(4, "杰");
            nicknameMale3Map.put(5, "飞");
            nicknameMale3Map.put(6, "贵");
            nicknameMale3Map.put(7, "昆");
            nicknameMale3Map.put(8, "涛");
            nicknameMale3Map.put(9, "军");
            nicknameMale3Map.put(10, "兵");
            nicknameMale3Map.put(11, "强");
            nicknameMale3Map.put(12, "八");
            nicknameMale3Map.put(13, "德");
            nicknameMale3Map.put(14, "宇");
            nicknameMale3Map.put(15, "明");
            nicknameMale3Map.put(16, "晖");
            nicknameMale3Map.put(17, "平");
            nicknameMale3Map.put(18, "雄");
            nicknameMale3Map.put(19, "鹏");
            nicknameMale3Map.put(20, "瑞");
            nicknameMale3Map.put(21, "行");
            nicknameMale3Map.put(22, "菱");
            nicknameMale3Map.put(23, "彰");
            nicknameMale3Map.put(24, "茂");
            nicknameMale3Map.put(25, "胤");
            nicknameMale3Map.put(26, "熙");
            nicknameMale3Map.put(27, "灿");
            nicknameMale3Map.put(28, "禧");
            nicknameMale3Map.put(29, "羲");
            nicknameMale3Map.put(30, "锐");
        }

        int key = random.nextInt(nicknameMale3Map.size());
        return nicknameMale3Map.get(key);
    }

    //
    private static HashMap<Integer, String> defaultAvatarMap, defaultMaleAvatarMap;

    public static String getDefaultAvatar(Integer sex) {

        if (random == null) {
            random = new Random();
        }

        if (sex != null && sex == 1) {

            if (defaultMaleAvatarMap == null) {
                defaultMaleAvatarMap = new HashMap<>();
                defaultMaleAvatarMap.put(0, "living/9999/default/user_default_avatar_m.png");
                defaultMaleAvatarMap.put(1, "living/9999/default/user_default_avatar_m1.png");
                defaultMaleAvatarMap.put(2, "living/9999/default/user_default_avatar_m2.png");
                defaultMaleAvatarMap.put(3, "living/9999/default/user_default_avatar_m3.png");
            }

            int key = random.nextInt(defaultMaleAvatarMap.size());
            return defaultMaleAvatarMap.get(key);
        } else {

            if (defaultAvatarMap == null) {
                defaultAvatarMap = new HashMap<>();
                defaultAvatarMap.put(0, "living/9999/default/user_default_avatar_f.png");
                defaultAvatarMap.put(1, "living/9999/default/user_default_avatar_f1.png");
                defaultAvatarMap.put(2, "living/9999/default/user_default_avatar_f2.png");
                defaultAvatarMap.put(3, "living/9999/default/user_default_avatar_f3.png");
            }
            int key = random.nextInt(defaultAvatarMap.size());
            return defaultAvatarMap.get(key);
        }
    }
}
