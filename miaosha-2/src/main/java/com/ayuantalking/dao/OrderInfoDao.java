package com.ayuantalking.dao;

import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.domain.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 23:26
 * @Version 1.0
 */
@Mapper
public interface OrderInfoDao {
    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = {"select last_inset_id()"})
    long create(OrderInfo orderInfo);

}
