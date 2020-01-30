package com.longxi.data.utils;

/**
 * @author longxi.cwl
 * @date 2019/11/04
 */
public class DfcUtil {

    public static void main(String[] args) {
        System.out.println(calcDfc(4, 140, 0.2, 0.02, 0.14));
        // 2345
        // 2185
        // 2098
    }

    /**
     * @param n
     * @param base
     * @param increase
     * @param sustainableIncrease
     * @param discount
     * @return
     */
    private static double calcDfc(int n, double base, double increase, double sustainableIncrease, double discount) {
        double dfc = 0;
        for (int i = 0; i < n; i++) {
            dfc += base * Math.pow((1 + increase), i + 1) / Math.pow((1 + discount), i + 1);
        }
        dfc += base * Math.pow((1 + increase), n) * (1 + sustainableIncrease) * (1 + discount) / (discount - sustainableIncrease) / Math.pow((1 + discount), n + 1);
        return dfc;
    }
}
