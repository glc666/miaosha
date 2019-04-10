package com.ayuantalking.mobile.controller;

import com.ayuantalking.service.GoodsService;
import com.ayuantalking.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 20:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/mobile/goods")
public class MobileGoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/goodsList")
    public List<GoodsVo> toGoodaList(){
        List<GoodsVo> goodsVoList = goodsService.findGoodsVoList();
        return goodsVoList;
    }

}
