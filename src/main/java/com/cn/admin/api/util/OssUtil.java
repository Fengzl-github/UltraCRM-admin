package com.cn.admin.api.util;

/**
 * @Author fengzl
 * @Date 2022/2/910:15
 * @Desc
 */
public class OssUtil {





    /**
     * @Author Fengzl
     * @Desc
     * @Date 10:18 2022/2/9
     * @param rand
     * @param n
     * @return java.lang.String
     **/
    public static String getRandomNumber(String rand, Integer n){


        return rand + Math.abs(n);
    }
}
