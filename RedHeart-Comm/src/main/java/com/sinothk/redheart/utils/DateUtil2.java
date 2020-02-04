package com.sinothk.redheart.utils;

import java.util.HashMap;
import java.util.Random;

public class DateUtil2 {

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
}
