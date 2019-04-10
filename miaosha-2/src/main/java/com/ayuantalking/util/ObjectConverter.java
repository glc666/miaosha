package com.ayuantalking.util;

import com.alibaba.fastjson.JSON;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/30/0030 11:44
 * @Version 1.0
 */
public class ObjectConverter {

    public static <T> String beanToString(T value){
        if(value == null){
            return null;
        }
        Class<?> aClass = value.getClass();
        if(int.class == aClass || Integer.class == aClass){
            return ""+value;
        } else if(long.class == aClass || Long.class == aClass){
            return ""+value;
        } else if (String.class == aClass){
            return String.valueOf(value);
        }
        return JSON.toJSONString(value);
    }

    public static <T>  T beanToString(String value,Class<T> clazz){
        if (value == null || value.length() <= 0 || clazz == null) {
            return null;
        }
        if(int.class == clazz || Integer.class == clazz){
            return (T) Integer.valueOf(value);
        } else if(long.class == clazz || Long.class == clazz){
            return (T) Long.valueOf(value);
        } else{
            return JSON.toJavaObject(JSON.parseObject(value),clazz);
        }
    }
}
