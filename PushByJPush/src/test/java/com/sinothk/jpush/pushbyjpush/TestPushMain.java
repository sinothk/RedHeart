//package com.sinothk.jpush.pushbyjpush;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//public class TestPushMain {
//
//    public static void main(String[] args) {
//
//    }
//
//    @Test
//    public void pushForAll() {
//        JPushHelper.pushForAll("111", "222", "333");
//    }
//
//    @Test
//    public void pushByAlias() {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("10000");
//        arrayList.add("10015");
//        JPushHelper.pushByAlias(arrayList, "111", "222", "333");
//    }
//
//    @Test
//    public void pushByRegId() {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("1507bfd3f7ae65dd3b3");
//        JPushHelper.pushByRegId(arrayList, "111", "222", "333");
//    }
//
//    @Test
//    public void pushMsgForAll() {
//        JPushHelper.pushMsgForAll("112");
//    }
//
//    @Test
//    public void pushMsgByAlias() {
//
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("10000");
//        arrayList.add("10015");
//        JPushHelper.pushMsgByAlias(arrayList, "发给10015用户");
//    }
//
//    @Test
//    public void pushMsgByRegId() {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("1507bfd3f7ae65dd3b3");
//        JPushHelper.pushMsgByRegId(arrayList, "555");
//    }
//}
