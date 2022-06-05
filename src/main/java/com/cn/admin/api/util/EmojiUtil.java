package com.cn.admin.api.util;

import com.cn.common.utils.MyString;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Fengzl
 * @Date 2022/5/20 10:05
 * @Desc
 */
public class EmojiUtil {

    static String[] emoji ={ "0xA9", "0xAE", "0x2122", "0x2196", "0x2197", "0x2198", "0x2199",
            "0x23E9", "0x23EA", "0x25B6", "0x25C0", "0x2600", "0x2601", "0x260E", "0x2614",
            "0x2615", "0x261D", "0x263A", "0x2648", "0x2649", "0x264A", "0x264B", "0x264C",
            "0x264D", "0x264E", "0x264F", "0x2650", "0x2651", "0x2652", "0x2653", "0x2660",
            "0x2663", "0x2665", "0x2666", "0x2668", "0x267F", "0x26A0", "0x26A1", "0x26BD",
            "0x26BE", "0x26C4", "0x26CE", "0x26EA", "0x26F2", "0x26F3", "0x26F5", "0x26FA",
            "0x26FD", "0x2702", "0x2708", "0x270A", "0x270B", "0x270C", "0x2728", "0x2733",
            "0x2734", "0x274C", "0x2754", "0x2755", "0x2764", "0x27A1", "0x27BF", "0x2B05",
            "0x2B06", "0x2B07", "0x2B55", "0x303D", "0x3297", "0x3299" };

    static List<String> emojies = Arrays.asList(emoji);

    /**
     * 检测是否有emoji字符
     *
     * @param source 原字符
     * @return boolean 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (MyString.isEmpty(source)) {
            return false;
        }
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isNotEmojiCharacter(codePoint)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isNotEmojiCharacter(char codePoint) {
        boolean flag = (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
        if (flag) {
            flag = !emojies
                    .contains("0x" + (Integer.toHexString(codePoint & 0xFFFF)).toUpperCase());
        }
        return flag;
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source 过滤emoji
     * @return String
     */
    public static String filterEmoji(String source) {
        if (!containsEmoji(source)) {
            return source;
        }
        // 到这里一定包含
        StringBuilder buf = new StringBuilder();
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                buf.append(codePoint);
            }
        }
        if (buf.length() == len) {
            buf = null;
            return source;
        } else {
            return buf.toString();
        }
    }


}
