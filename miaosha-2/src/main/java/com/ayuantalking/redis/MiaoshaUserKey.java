package com.ayuantalking.redis;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/25/0025 22:35
 * @Version 1.0
 */
public class MiaoshaUserKey extends BaseKeyPrefix {

    public final static int USER_TOKEN_EXPREDSECONDS = 60*60*24;

    public MiaoshaUserKey(int expredSeconds, String prefix) {
        super(expredSeconds, prefix);
    }

    public static MiaoshaUserKey token = new MiaoshaUserKey(USER_TOKEN_EXPREDSECONDS,"USER_TOKEN:");
}
