package com.ayuantalking.redis;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 09:42
 * @Version 1.0
 */
public interface KeyPrefix {
    /**
     * 过期时间
     * @return
             */
    public int expredSecond();

    /**
     * 获取key
     * @return
     */
    public String getPrefix();
}
