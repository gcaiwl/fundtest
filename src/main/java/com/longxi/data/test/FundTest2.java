package com.longxi.data.test;

/**
 * @author longxi.cwl
 * @date 2017/11/24
 */
public class FundTest2 {

    public static void main(String[] args) {
        String a = "  2015年2季度债券投资明细";
        a = a.replaceAll(".*(\\d)季度.*", "$1");
        System.out.println(a);
    }
}
