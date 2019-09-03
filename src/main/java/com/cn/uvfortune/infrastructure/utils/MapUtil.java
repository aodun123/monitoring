package com.cn.uvfortune.infrastructure.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

    /**
     * 将map转化为 实体对象  属性大小写不敏感
     *
     * @param map
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        T t = clazz.newInstance();
        Map<String, Object> newMap = new HashMap<String, Object>();
        map.forEach((key, value) -> {
            newMap.put(key.toLowerCase(), value);
        });
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("set")) {
                String pName = name.substring(3).toLowerCase();
                Object object = newMap.get(pName);
                if (object instanceof BigDecimal) {
                    boolean isInt = method.getParameterTypes()[0] == Integer.class;
                    if (isInt) {
                        method.invoke(t, Integer.parseInt(object.toString()));
                    } else {
                        method.invoke(t, ((BigDecimal) object).doubleValue());
                    }
                } else {
                    method.invoke(t, object);
                }
            }
        }
        return t;
    }

    /**
     * 将List<map>转化为 实体集合  属性大小写不敏感
     *
     * @param list
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> List<T> listMapToListObj(List<Map<String, Object>> list, Class<T> clazz) {
        List<T> retList = new ArrayList<T>();
        list.forEach(item -> {
            try {
                retList.add(mapToObject(item, clazz));
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return retList;
    }

    /**
     * 将实体转换为Map
     */
    public static Map<String, Object> objToMap(Object obj) {
        Class clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        Map<String, Object> map = new HashMap<>();
        try {
            for (Method method : methods) {
                String name = method.getName();
                if (name.startsWith("get") && !name.equals("getClass")) {
                    Object val = method.invoke(obj);
                    if (val != null) {
                        String pname = name.substring(3).toLowerCase();
                        map.put(pname, val);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
