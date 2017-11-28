package com.longxi.data.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longxi.cwl
 * @date 2017/11/28
 */
public class FundTest4 {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i < 10000000; i++) {
            int key = i % 128;
            Integer num = map.get(key);
            if (null == num) {
                num = 0;
            }
            map.put(key, ++num);
        }

        int max = 0;
        int min = 999999;
        for (Integer key : map.keySet()) {
            if (max < map.get(key)) {
                max = map.get(key);
            }

            if (min > map.get(key)) {
                min = map.get(key);
            }
            System.out.println(key + " -> " + map.get(key));
        }
        System.out.println(max + " -> " + min);
    }
}
