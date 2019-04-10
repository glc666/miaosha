package com.ayuantalking.service.impl;

import com.ayuantalking.dao.GoodsVoDao;
import com.ayuantalking.service.GoodsVoService;
import com.ayuantalking.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/28/0028 08:50
 * @Version 1.0
 */
@Service
public class GoodsVoServiceImpl implements GoodsVoService {

    @Autowired
    GoodsVoDao goodsVoDao;

    @Override
    public List<GoodsVo> findGoodsVoList() {
        return goodsVoDao.findGoodsVoList();
    }

    @Override
    public GoodsVo findGoodsVoByGoodsId(long goodsId) {
        return goodsVoDao.findGoodsVoByGoodsId(goodsId);
    }

    @Override
    public int reduceStock(long goodsId) {
        return goodsVoDao.reduceStock(goodsId);
    }
}
