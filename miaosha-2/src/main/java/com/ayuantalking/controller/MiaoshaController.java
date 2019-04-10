package com.ayuantalking.controller;

import com.ayuantalking.config.NeedLogin;
import com.ayuantalking.domain.MiaoshaGoods;
import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.redis.GoodsDetailKey;
import com.ayuantalking.redis.MiaoshaGoodsKey;
import com.ayuantalking.result.CodeMsg;
import com.ayuantalking.service.GoodsService;
import com.ayuantalking.service.MiaoshaOrderService;
import com.ayuantalking.service.MiaoshaService;
import com.ayuantalking.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 20:51
 * @Version 1.0
 */
@Controller
@RequestMapping("/miaosha")
@Slf4j
public class MiaoshaController implements InitializingBean {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MiaoshaService miaoshaService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/doMiaosha")
    @NeedLogin
    public String doMiaosha(Model model, @Param("goodsId") long goodsId, MiaoshaUser miaoshaUser){
        String key = MiaoshaGoodsKey.goodsStock.getPrefix()+":"+goodsId;
        // 减库存
        Long decr = redisTemplate.getConnectionFactory().getConnection().decr(redisTemplate.getKeySerializer().serialize(key));
        if(decr > 0){
            // 查询订单信息
            GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
            // 判断库存是否大于0
            if (goodsVo != null) {
                Integer stockCount = goodsVo.getStockCount();
                if (stockCount <= 0){
                    model.addAttribute("errorMessage", CodeMsg.SELL_OUT.getMsg());
                    return "miaoshaFail";
                }
            }
            Long userId = miaoshaUser.getId();
            // 判断是否重复秒杀
            MiaoshaOrder miaoshaOrder = null;//miaoshaService.findMiaoshaOrderByUserIdAndGoodsId(userId,goodsId);
            // 减库存 新增订单
            if (miaoshaOrder != null){
                model.addAttribute("errorMessage", CodeMsg.REPEAT_SPIKE.getMsg());
                return "miaoshaFail";
            }

            miaoshaService.miaosha(miaoshaUser,goodsVo);

        }
        model.addAttribute("errorMessage", CodeMsg.SUCCESS.getMsg());

        return "miaoshaFail";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
       /** List<GoodsVo> goodsVoList = goodsService.findGoodsVoList();
        for (GoodsVo goodsVo:goodsVoList ) {
            String key = MiaoshaGoodsKey.goodsStock.getPrefix()+":"+goodsVo.getGoodsId();
            log.info("===="+key);
            log.info("===="+redisTemplate.getKeySerializer().serialize(key));
            log.info(""+goodsVo.getStockCount());
            redisTemplate.getConnectionFactory().getConnection().incrBy( redisTemplate.getKeySerializer().serialize(key),goodsVo.getStockCount());
        }*/
    }
}
