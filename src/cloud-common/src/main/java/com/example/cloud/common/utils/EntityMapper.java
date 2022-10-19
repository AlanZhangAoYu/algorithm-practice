package com.example.cloud.common.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * entity与map转换
 *
 * @author 蒙臣
 * @serial 2019-12-27
 */
public class EntityMapper {

    public static <T> Map<String, Object> EntityToMap(T t) {
        Map<String, Object> map = new HashMap();
        if (ObjectUtils.isEmpty(t)) {
            return map;
        }
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            field.setAccessible(true);
            JsonFormat annotation = field.getAnnotation(JsonFormat.class);
            try {
                if (annotation != null && field.get(t) != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(annotation.pattern());
                    map.put(field.getName(), dateFormat.format(field.get(t)));
                } else {
                    if (ObjectUtils.isEmpty(field.get(t))) {
                        continue;
                    }
                    map.put(field.getName(), field.get(t));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> EntityToMapAndNotNull(List<T> t) {
        Map<String, Object> map = new HashMap();
        for (T t1 : t) {
            if (ObjectUtils.isEmpty(t1)) {
                continue;
            }
            Field[] fields = t1.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                JsonFormat annotation = field.getAnnotation(JsonFormat.class);
                try {
                    if (annotation != null && field.get(t1) != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(annotation.pattern());
                        map.put(field.getName(), dateFormat.format(field.get(t1)));
                    } else {
                        if (field.get(t1) == null || field.get(t1).toString() == null || "".equals(field.get(t1).toString())) {
                            continue;
                        }
                        map.put(field.getName(), field.get(t1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * 传入MAP去除其中的空串或者空对象
     *
     * @param map
     * @param <T>
     * @return
     */
    public static <T> Map mapRemoveNull(Map map) {
        Map resultMap = new HashMap();
        for (Object set : map.keySet()) {
            Object s = map.get(set);
            if (ObjectUtils.isNotEmpty(s)) {
                resultMap.put(set, String.valueOf(s).trim());
            }
        }
        return resultMap;
    }
}
