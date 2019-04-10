package com.ayuantalking.service.impl;

import com.ayuantalking.service.MiaoshaGoodsService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 21:58
 * @Version 1.0
 */
@Service
public class MiaoshaGoodsServiceImpl implements MiaoshaGoodsService {
    @Override
    public int reduceStock(Long goodsId) {
        return 0;
    }
}
