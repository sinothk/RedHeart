package com.sinothk.jpush.pushbyjpush;

import org.junit.Test;

import java.util.ArrayList;

public class TestPushMain {

    public static void main(String[] args) {

    }

    @Test
    public void pushAll() {
        JPushHelper.pushAll("111", "222", "333");
    }

    @Test
    public void pushByAlias() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("10001");
        arrayList.add("10015");
        JPushHelper.pushByAlias(arrayList, "111", "222", "333");
    }

    @Test
    public void pushByRegId() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1507bfd3f7ae65dd3b3");
        JPushHelper.pushByRegId(arrayList, "111", "222", "333");
    }
}
