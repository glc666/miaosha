package com.ayuantalking.dao;

import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/28/0028 10:27
 * @Version 1.0
 */
@Mapper
public interface GoodsOrderDao {

    @Select(" SELECT * FROM miaosha_order WHERE userId = #{userId} and goodsId = #{goodsId} ")
    MiaoshaOrder findGoodsOrderByUserIdAndGoodsId(@Param("userId") Long userId,@Param("goodsId") long goodsId);

    @Insert(" INSERT INTO miaosha.order_Info" +
            " (userId, goodsId, deliveryAddrId, goodsName, goodsCount, goodsPrice, orderChannel, status, createDate) " +
            " VALUES (#{userId}, #{goodsId}, #{deliveryAddrId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = "select last_insert_id()")
    long addOrderInfo(OrderInfo orderInfo);

    @Insert(" INSERT INTO miaosha.miaosha_order(userId, orderId, goodsId) VALUES (#{userId}, #{orderId}, #{goodsId})")
    void addMiaoshaoOrder(MiaoshaOrder miaoshaOrder);
}
