package com.ayuantalking.service.impl;

import com.ayuantalking.dao.GoodsVoDao;
import com.ayuantalking.service.GoodsService;
import com.ayuantalking.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 21:05
 * @Version 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {

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
}
