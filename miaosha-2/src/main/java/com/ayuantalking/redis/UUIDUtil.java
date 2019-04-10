package com.ayuantalking.redis;

import org.junit.Test;

import java.util.UUID;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/25/0025 23:08
 * @Version 1.0
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
