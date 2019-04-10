package com.ayuantalking.controller;

import com.ayuantalking.annotation.NeedLogin;
import com.ayuantalking.domain.MiaoshaMessage;
import com.ayuantalking.domain.MiaoshaOrder;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.redis.GoodsStockKeyPrefix;
import com.ayuantalking.result.CodeMsg;
import com.ayuantalking.service.GoodsOrderService;
import com.ayuantalking.service.GoodsVoService;
import com.ayuantalking.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 10:24
 * @Version 1.0
 */
@Controller
@RequestMapping("/miaosha")
@Slf4j
public class MiaoshaController  implements InitializingBean {

    public HashMap<Long, Boolean> localOverMap =  new HashMap<Long, Boolean>();

    private final static String goodsStockKey="goodsStockKey";

    @Autowired
    private GoodsVoService goodsVoService;

    @Autowired
    private GoodsOrderService goodsOrderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    @RequestMapping("/{path}/doMiaosha")
    @NeedLogin
    public String doMiaosha(@RequestParam("goodsId") long goodsId, Model model, MiaoshaUser miaoshaUser){

        Boolean aBoolean = localOverMap.get(goodsId);
        if(aBoolean){
            model.addAttribute("message", CodeMsg.GOODS_SELL_OUT.getMsg());
            return "miaoshaResult";
        }
        // 判断是否登录
        GoodsVo goodsVo = goodsVoService.findGoodsVoByGoodsId(goodsId);

        // 判断是否重复秒杀
        MiaoshaOrder miaoshaOrder = goodsOrderService.findGoodsOrderByUserIdAndGoodsId(miaoshaUser.getId(),goodsId);
        if(miaoshaOrder != null){
            model.addAttribute("message", CodeMsg.REPEAT_SECKILL.getMsg());
            return "miaoshaResult";
        }
        String key = GoodsStockKeyPrefix.goodsStock.getPrefix()+":"+goodsId;
        String redisGoodsStock = stringRedisTemplate.opsForValue().get(key);
        // long goodsStock = Long.valueOf(redisGoodsStock);

        Long increment = stringRedisTemplate.opsForValue().increment(key, -1);//10
        if(increment < 0){
            localOverMap.put(goodsId,true);
            model.addAttribute("message", CodeMsg.GOODS_SELL_OUT.getMsg());
            return "miaoshaResult";
        }
        log.info("当前库存：{}",increment);
        MiaoshaMessage message = new MiaoshaMessage();
        message.setUser(miaoshaUser);
        message.setGoodsVo(goodsVo);
        GenericMessage<MiaoshaMessage> miaoshaMessageGenericMessage = new GenericMessage<MiaoshaMessage>(message);
        jmsMessagingTemplate.send("domiaosha",miaoshaMessageGenericMessage);

        // 减库存 下订单
        // goodsOrderService.domiaosha(miaoshaUser,goodsVo);
        model.addAttribute("message", CodeMsg.SUCCESS.getMsg());
        log.info("库存不足");

        return "miaoshaResult";
    }



    @RequestMapping("/doMiaosha2")
    @NeedLogin
    public String doMiaosha2(@RequestParam("goodsId") long goodsId, Model model, MiaoshaUser miaoshaUser){
        String key = GoodsStockKeyPrefix.goodsStock.getPrefix()+":"+goodsId;
        String redisGoodsStock = stringRedisTemplate.opsForValue().get(key);
        long goodsStock = Long.valueOf(redisGoodsStock);
        log.info("当前库存：{}",goodsStock);
        if(goodsStock > 0){
            log.info("库存大于0，开始秒杀");
            // 判断是否登录
            GoodsVo goodsVo = goodsVoService.findGoodsVoByGoodsId(goodsId);

            // 判断是否重复秒杀
            MiaoshaOrder miaoshaOrder = goodsOrderService.findGoodsOrderByUserIdAndGoodsId(miaoshaUser.getId(),goodsId);
            if(miaoshaOrder != null){
                model.addAttribute("message", CodeMsg.REPEAT_SECKILL.getMsg());
                return "miaoshaResult";
            }

            Long increment = stringRedisTemplate.opsForValue().increment(goodsStockKey, -1);
            log.info("当前库存：{}",increment);

            MiaoshaMessage message = new MiaoshaMessage();
            message.setUser(miaoshaUser);
            message.setGoodsVo(goodsVo);
            GenericMessage<MiaoshaMessage> miaoshaMessageGenericMessage = new GenericMessage<MiaoshaMessage>(message);
            jmsMessagingTemplate.send("domiaosha",miaoshaMessageGenericMessage);

            // 减库存 下订单
            // goodsOrderService.domiaosha(miaoshaUser,goodsVo);
            model.addAttribute("message", CodeMsg.SUCCESS.getMsg());
        }
        log.info("库存不足");

        return "miaoshaResult";
    }


    @RequestMapping("/doMiaosha1")
    @NeedLogin
    public String doMiaosha1(@RequestParam("goodsId") long goodsId, Model model, MiaoshaUser miaoshaUser){

        // Object goodsStockStr = stringRedisTemplate.opsForValue().get(goodsStockKey);
        String goodsStockStr = stringRedisTemplate.opsForValue().get(goodsStockKey+":"+goodsId);
        log.info("从redis中取出库存：{}",goodsStockStr);
        if(goodsStockStr != null){
            log.info("goodsStockStr 不为空 ，转long");
            long goodsStock = Long.parseLong(goodsStockStr+"");
            log.info("当前库存{}",goodsStock);
            if(goodsStock >0 ){
                log.info("当前库存{}",goodsStock);
                stringRedisTemplate.opsForValue().increment(goodsStockKey+""+goodsId,-1);
                // 判断是否登录
                GoodsVo goodsVo = goodsVoService.findGoodsVoByGoodsId(goodsId);
                if (goodsVo == null){
                    model.addAttribute("message", CodeMsg.GOODS_NOT_EXIST.getMsg());
                    return "miaoshaResult";
                }
                // 库存是否大于0
                if (goodsVo.getStockCount() <=0 ){
                    model.addAttribute("message", CodeMsg.GOODS_SELL_OUT.getMsg());
                    return "miaoshaResult";
                }

                // 判断是否重复秒杀
                MiaoshaOrder miaoshaOrder = goodsOrderService.findGoodsOrderByUserIdAndGoodsId(miaoshaUser.getId(),goodsId);
                if(miaoshaOrder != null){
                    model.addAttribute("message", CodeMsg.REPEAT_SECKILL.getMsg());
                    return "miaoshaResult";
                }
                MiaoshaMessage message = new MiaoshaMessage();
                message.setUser(miaoshaUser);
                message.setGoodsVo(goodsVo);
                GenericMessage<MiaoshaMessage> miaoshaMessageGenericMessage = new GenericMessage<MiaoshaMessage>(message);

                jmsMessagingTemplate.send("my_msg",miaoshaMessageGenericMessage);
                // 减库存 下订单
                goodsOrderService.domiaosha(miaoshaUser,goodsVo);
                model.addAttribute("message", CodeMsg.SUCCESS.getMsg());
            }
        }
        return "miaoshaResult";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVoList = goodsVoService.findGoodsVoList();
        for (GoodsVo goodsVo : goodsVoList) {
            log.info("初始化库存-------------{},{}",goodsStockKey+""+goodsVo.getGoodsId(),goodsVo.getStockCount());
            String key = GoodsStockKeyPrefix.goodsStock.getPrefix()+":"+goodsVo.getGoodsId();
            stringRedisTemplate.delete(key);
            log.info("商品库存redisKey:{}",key);
            //stringRedisTemplate.getConnectionFactory().getConnection().incrBy(stringRedisTemplate.getKeySerializer().serialize(key), value )
            stringRedisTemplate.opsForValue().increment(key,goodsVo.getStockCount());
            localOverMap.put(goodsVo.getGoodsId(),false);
        }
    }

}
