package com.ayuantalking.redis;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 09:44
 * @Version 1.0
 */
public abstract class BaseKeyPrefix implements KeyPrefix {

    private int expredSecond;
    private String prefix;

    public BaseKeyPrefix(){

    }

    public int getExpredSecond(){
        return this.expredSecond;
    }

    public String getPrefix(){
        String name = getClass().getName().toString();
        return name+"@"+prefix;
    }


    public BaseKeyPrefix(String prefix){
        this.expredSecond=0;//永不过期
        this.prefix = prefix;
    }
    public BaseKeyPrefix(int expredSecond,String prefix){
        this.expredSecond = expredSecond;
        this.prefix = prefix;
    }


}
