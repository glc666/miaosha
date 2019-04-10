package com.ayuantalking.redis;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/25/0025 22:35
 * @Version 1.0
 */
public class MiaoshaGoodsKey extends BaseKeyPrefix {


    public MiaoshaGoodsKey(String prefix) {
        super(prefix);
    }

    public static MiaoshaGoodsKey goodsStock = new MiaoshaGoodsKey("miaosha_goods_stockcount");
}
