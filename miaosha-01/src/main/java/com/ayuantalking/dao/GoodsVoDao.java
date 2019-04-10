package com.ayuantalking.dao;

import com.ayuantalking.vo.GoodsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/28/0028 08:51
 * @Version 1.0
 */
@Mapper
public interface GoodsVoDao {

    @Select(" SELECT mg.goodsId,mg.miaoshaPrice,mg.stockCount,mg.startDate,mg.endDate,gd.id,gd.goodsName,gd.goodsTitle " +
            " ,gd.goodsImg,gd.goodsDetail,gd.goodsPrice,gd.goodsStock FROM miaosha_goods mg LEFT JOIN goods gd on mg.goodsId =  gd.id ")
    List<GoodsVo> findGoodsVoList();

    @Select(" SELECT mg.goodsId,mg.miaoshaPrice,mg.stockCount,mg.startDate,mg.endDate,gd.id,gd.goodsName,gd.goodsTitle " +
            " ,gd.goodsImg,gd.goodsDetail,gd.goodsPrice,gd.goodsStock FROM miaosha_goods mg LEFT JOIN goods gd on mg.goodsId =  gd.id  " +
            " where mg.goodsId = #{goodsId} ")
    GoodsVo findGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update(" UPDATE miaosha_goods SET stockCount = stockCount - 1 WHERE goodsId = #{goodsId} and stockCount > 0 ")
    int reduceStock(@Param("goodsId") Long goodsId);
}
