package com.ayuantalking.service;

import com.ayuantalking.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/28/0028 08:49
 * @Version 1.0
 */
public interface GoodsVoService {

    List<GoodsVo> findGoodsVoList();

    GoodsVo findGoodsVoByGoodsId(long goodsId);

    int reduceStock(long goodsId);
}
