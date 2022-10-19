package com.example.cloud.common.utils;

/**
 * 毫秒转换成 天 时 分 秒格式
 */
public class MsToDHMM {
    private static final int SECOND = 1000;

    private static final int MINUTE = 60 * SECOND;

    private static final int HOUR = 60 * MINUTE;

    private static final int DAY = 24 * HOUR;

    public static String getDhmm(long ms) {
        StringBuffer text = new StringBuffer("");
        if (ms > DAY){
            text.append(ms / DAY).append("天 ");
            ms %= DAY;
        }
        if (ms > HOUR){
            text.append(ms / HOUR).append("时 ");
            ms %= HOUR;
        }
        if (ms > MINUTE){
            text.append(ms / MINUTE).append("分 ");
            ms %= MINUTE;
        }
        if (ms > SECOND){
            text.append(ms / SECOND).append("秒 ");
        }
        if (ms < SECOND){
            text.append("0秒");
        }
//        text.append(ms + " ms");
        return text.toString();
    }

}
