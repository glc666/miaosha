package com.ayuantalking.dao;

import com.ayuantalking.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 21:07
 * @Version 1.0
 */
@Mapper
public interface GoodsVoDao {
    @Select(" SELECT goods.*,mg.miaoshaPrice,mg.goodsId,mg.endDate,mg.startDate,mg.miaoshaPrice,mg.stockCount FROM miaosha_goods mg LEFT JOIN goods on mg.goodsId = goods.id ")
    List<GoodsVo> findGoodsVoList();


    @Select(" SELECT goods.*,mg.miaoshaPrice,mg.goodsId,mg.endDate,mg.startDate,mg.miaoshaPrice,mg.stockCount FROM miaosha_goods mg LEFT JOIN goods on mg.goodsId = goods.id where mg.goodsId = #{goodsId}")
    GoodsVo findGoodsVoByGoodsId(@Param("goodsId") long goodsId);

}
