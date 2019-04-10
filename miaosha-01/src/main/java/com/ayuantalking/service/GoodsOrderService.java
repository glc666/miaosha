package com.ayuantalking.service;

import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.vo.GoodsVo;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/28/0028 10:21
 * @Version 1.0
 */
public interface GoodsOrderService {
    MiaoshaOrder findGoodsOrderByUserIdAndGoodsId(Long id, long goodsId);

    void domiaosha(MiaoshaUser miaoshaUser, GoodsVo goodsVo);

}
