package com.ayuantalking.redis;


/**
 * @Author: ayuantalking
 * @Date: 2019/3/25/0025 22:21
 * @Version 1.0
 */
public abstract class BaseKeyPrefix implements KeyPrefix {

    private int expredSeconds;
    private String prefix;

    public int expredSeconds() {
        return this.expredSeconds;
    }

    public String getPrefix() {
        String className = getClass().getName();
        return className+":"+this.prefix;
    }

    public BaseKeyPrefix(String prefix){
        this.expredSeconds = 0;
        this.prefix = prefix;
    }

    public BaseKeyPrefix(int expredSeconds,String prefix){
        this.expredSeconds = expredSeconds;
        this.prefix = prefix;
    }


}
