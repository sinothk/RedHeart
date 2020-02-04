package com.sinothk.redheart.utils;

import java.util.HashMap;
import java.util.Random;

public class SystemDefaultData {


    private static HashMap<Integer, String> yearStrMap = null;

    public static String getOneYear() {

        if (yearStrMap == null) {
            yearStrMap = new HashMap<>();

            yearStrMap.put(0, "1980-01-05");
            yearStrMap.put(1, "1983-02-06");
            yearStrMap.put(2, "1985-03-08");
            yearStrMap.put(3, "1987-04-05");
            yearStrMap.put(4, "1988-05-25");
            yearStrMap.put(5, "1989-06-15");
            yearStrMap.put(6, "1990-07-20");
            yearStrMap.put(7, "1990-08-05");
            yearStrMap.put(8, "1992-09-15");
            yearStrMap.put(9, "1993-10-25");
        }

        int num = new Random().nextInt(yearStrMap.size());
        return yearStrMap.get(num);
    }

    private static Random random = null;
    private static HashMap<Integer, String> oneWordMap, twoWordMap, threeWordMap, fourWordMap, fiveWordMap;


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
        int key = random.nextInt(oneWordMap.size());
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
}
