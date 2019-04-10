package com.ayuantalking.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 21:57
 * @Version 1.0
 */
@Mapper
public interface MiaoshaGoodsService {

    @Update(" UPDATE miaosha_goods SET stockCount = stockCount -1 WHERE goodsId = #{goodsId} ")
    int reduceStock(@Param("goodsId") Long goodsId);
}
