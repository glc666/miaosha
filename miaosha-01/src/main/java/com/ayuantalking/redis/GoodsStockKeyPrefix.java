package com.ayuantalking.redis;

import java.util.HashMap;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 09:54
 * @Version 1.0
 */
public class GoodsStockKeyPrefix extends BaseKeyPrefix {

    public static String prefix = "goodsStock";

    @Override
    public int expredSecond() {
        return 0;
    }

    public GoodsStockKeyPrefix(){

    }

    public GoodsStockKeyPrefix(String prefix){
        super(prefix);
        this.prefix=prefix;
    }
    public static GoodsStockKeyPrefix goodsStock = new GoodsStockKeyPrefix(prefix);

    public HashMap<Long, Boolean> localOverMap =  new HashMap<Long, Boolean>();



}
