package com.ayuantalking.redis;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/25/0025 22:35
 * @Version 1.0
 */
public class GoodsDetailKey extends BaseKeyPrefix {


    public GoodsDetailKey(String prefix) {
        super(prefix);
    }

    public static GoodsDetailKey goodsList = new GoodsDetailKey("GOODS_DETAIL:");
}
