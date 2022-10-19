package com.example.cloud.common.utils;

import java.util.Collection;

public class IdUtils {

    public static String getIds(Collection collection){
        String str ="";
        if(collection.size()>0){
            for (Object s : collection) {
                str += ",'"+ s +"'";
            }
            str = str.substring(1);
        }
        return str;
    }

    public static String getIds(String[] ids){
        String str ="";
        if(ids.length>0){
            for (String s : ids) {
                str += ",'"+ s+"'";
            }
            str = str.substring(1);
        }
        return str;
    }

    public static String getIds(Integer[] ids){
        String str ="";
        if(ids.length>0){
            for (int s : ids) {
                str += ",'"+ s+"'";
            }
            str = str.substring(1);
        }
        return str;
    }
}
