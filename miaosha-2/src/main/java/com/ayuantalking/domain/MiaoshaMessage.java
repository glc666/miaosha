package com.ayuantalking.domain;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/29/0029 22:22
 * @Version 1.0
 */
public class MiaoshaMessage {

    private MiaoshaUser user;
    private long goodsId;
    public MiaoshaUser getUser() {
        return user;
    }
    public void setUser(MiaoshaUser user) {
        this.user = user;
    }
    public long getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
