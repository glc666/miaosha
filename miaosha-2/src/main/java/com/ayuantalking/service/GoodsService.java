package com.ayuantalking.service;

import com.ayuantalking.vo.GoodsVo;

import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 21:01
 * @Version 1.0
 */
public interface GoodsService {
    List<GoodsVo> findGoodsVoList();
    GoodsVo findGoodsVoByGoodsId(long goodsId);
}
