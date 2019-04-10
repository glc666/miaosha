package com.ayuantalking.controller;

import com.ayuantalking.config.NeedLogin;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.redis.GoodsDetailKey;
import com.ayuantalking.redis.MiaoshaUserKey;
import com.ayuantalking.service.GoodsService;
import com.ayuantalking.service.MiaoshaUserService;
import com.ayuantalking.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 20:51
 * @Version 1.0
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    ApplicationContext applicationContext;
    @RequestMapping("/goodsList")
    @NeedLogin
    public String toGoodaList(Model model, MiaoshaUser miaoshaUser){

        List<GoodsVo> goodsVoList = goodsService.findGoodsVoList();
        model.addAttribute("goodsList",goodsVoList);
        model.addAttribute("miaoshaUser",miaoshaUser);
        return "goodsList";
    }

    @RequestMapping("/goodsDetailExt/{goodsId}")
    @ResponseBody
    public String goodsDetailExt(HttpServletRequest request, HttpServletResponse response,Model model, @PathVariable(value = "goodsId",required = true) Long goodsId){
       String html = redisTemplate.opsForValue().get(GoodsDetailKey.goodsList.getPrefix()+goodsId)+"";
       log.info("html=================================="+html);
       if(html == null || "null".equals(html)){
           log.info("查询redis=====================================================================");
           GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
           // 剩余时间
           long remainTime = 0;
           int miaoshaStatus = 0;
           if (goodsVo != null) {
               long startTime = goodsVo.getStartDate().getTime();
               long endTime = goodsVo.getEndDate().getTime();
               long now = System.currentTimeMillis();

               if (startTime > now){//秒杀还未开始。开始倒计时
                   remainTime = (endTime - startTime)/1000;
                   miaoshaStatus = 0;// 未开始
               } else if(endTime < now){ // 秒杀已结束
                   remainTime = -1;
                   miaoshaStatus = 2;
               } else { // 正在秒杀
                   remainTime = 0;
                   miaoshaStatus = 1;
               }
           }

           model.addAttribute("goodsVo",goodsVo);
           model.addAttribute("remainTime",remainTime);
           model.addAttribute("miaoshaStatus",miaoshaStatus);


           SpringTemplateEngine templateEngine = thymeleafViewResolver.getTemplateEngine();
           SpringWebContext springWebContext = new SpringWebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap(),applicationContext);

           html = templateEngine.process("goodsDetail",springWebContext);
           redisTemplate.opsForValue().set(GoodsDetailKey.goodsList.getPrefix()+goodsId,html);
       }
        return html;
    }

    @RequestMapping("/goodsDetail/{goodsId}")
    public String goodsDetail(Model model,@PathVariable(value = "goodsId",required = true) Long goodsId){
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        // 剩余时间
        long remainTime = 0;
        int miaoshaStatus = 0;
        if (goodsVo != null) {
            long startTime = goodsVo.getStartDate().getTime();
            long endTime = goodsVo.getEndDate().getTime();
            long now = System.currentTimeMillis();

            if (startTime > now){//秒杀还未开始。开始倒计时
                remainTime = (endTime - startTime)/1000;
                miaoshaStatus = 0;// 未开始
            } else if(endTime < now){ // 秒杀已结束
                remainTime = -1;
                miaoshaStatus = 2;
            } else { // 正在秒杀
                remainTime = 0;
                miaoshaStatus = 1;
            }
        }

        model.addAttribute("goodsVo",goodsVo);
        model.addAttribute("remainTime",remainTime);
        model.addAttribute("miaoshaStatus",miaoshaStatus);


        return "goodsDetail";
    }

}
