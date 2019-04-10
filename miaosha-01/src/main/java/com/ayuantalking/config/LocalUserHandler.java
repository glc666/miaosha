package com.ayuantalking.config;

import com.ayuantalking.domain.MiaoshaUser;

import java.util.HashMap;

/**
 * @Author: ayuantalking
 * @Date: 2019/4/2/0002 20:55
 * @Version 1.0
 */
public class LocalUserHandler {

    public static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();

    public static void setUser(MiaoshaUser miaoshaUser){
        userHolder.set(miaoshaUser);

    }
    public static MiaoshaUser getUser(){
        return userHolder.get();
    }


}
