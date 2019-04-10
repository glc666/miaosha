package com.ayuantalking.domain;

import com.ayuantalking.vo.GoodsVo;

import java.io.Serializable;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/29/0029 22:22
 * @Version 1.0
 */
public class MiaoshaMessage implements Serializable {

    private MiaoshaUser user;
    private GoodsVo goodsVo;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }
}
