package com.ayuantalking.util;

import java.util.UUID;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 10:10
 * @Version 1.0
 */
public class UUIDUtil {


    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
