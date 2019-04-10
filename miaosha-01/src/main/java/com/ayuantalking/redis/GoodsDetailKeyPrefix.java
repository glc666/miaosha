package com.ayuantalking.redis;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 09:54
 * @Version 1.0
 */
public class GoodsDetailKeyPrefix extends BaseKeyPrefix {

    public static String prefix = "goodsDetail";

    @Override
    public int expredSecond() {
        return 0;
    }

    public GoodsDetailKeyPrefix(){

    }

    public GoodsDetailKeyPrefix(String prefix){
        super(prefix);
        this.prefix=prefix;
    }
    public static GoodsDetailKeyPrefix goodsDetail = new GoodsDetailKeyPrefix(prefix);


}
