package com.ayuantalking.redis;

import org.junit.Test;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 09:54
 * @Version 1.0
 */
public class MiaoshaUserKeyPrefix extends BaseKeyPrefix {

    public static int expredSecond = 3600*24;
    public static String prefix = "tk";

    @Override
    public int expredSecond() {
        return this.expredSecond;
    }

    public MiaoshaUserKeyPrefix(){

    }

    public MiaoshaUserKeyPrefix(int expredSecond,String prefix){
        super(expredSecond,prefix);
        this.expredSecond = expredSecond;
        this.prefix=prefix;
    }
    public static MiaoshaUserKeyPrefix token = new MiaoshaUserKeyPrefix(expredSecond,prefix);


}
