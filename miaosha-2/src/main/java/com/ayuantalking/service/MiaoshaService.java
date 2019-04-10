package com.ayuantalking.service;

import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.vo.GoodsVo;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 22:31
 * @Version 1.0
 */
public interface MiaoshaService {
    void miaosha(MiaoshaUser miaoshaUser, GoodsVo goodsVo);
}
