package com.ayuantalking.service;

import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 21:50
 * @Version 1.0
 */
public interface MiaoshaOrderService {

    MiaoshaOrder findMiaoshaOrderByUserIdAndGoodsId(Long userId,long goodsId);

    void createOrder(MiaoshaOrder miaoshaOrder);
}
