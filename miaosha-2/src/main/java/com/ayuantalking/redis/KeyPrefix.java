package com.ayuantalking.redis;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/25/0025 22:20
 * @Version 1.0
 */
public interface KeyPrefix {

    public int expredSeconds();

    public String getPrefix();
}
