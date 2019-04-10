package com.ayuantalking.service;

import com.ayuantalking.domain.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 22:41
 * @Version 1.0
 */
@Mapper
public interface OrderService {


    Long create(OrderInfo orderInfo);
}
