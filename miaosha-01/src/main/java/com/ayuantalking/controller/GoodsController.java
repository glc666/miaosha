package com.ayuantalking.controller;

import com.ayuantalking.annotation.NeedLogin;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.redis.GoodsDetailKeyPrefix;
import com.ayuantalking.service.GoodsVoService;
import com.ayuantalking.service.MiaoshaUserService;
import com.ayuantalking.vo.GoodsVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 10:24
 * @Version 1.0
 */
@Api(value="/goods", tags="商品模块")
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private GoodsVoService goodsVoService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value="获取商品详情", notes="获取用户列表notes")
    @RequestMapping(value = "/goodsDetail/{goodsId}",method = RequestMethod.GET)
    @NeedLogin
    @ResponseBody
    public String goodsDetail(HttpServletRequest request, HttpServletResponse response, MiaoshaUser miaoshaUser, Model model, @PathVariable("goodsId") long goodsId){
        if(miaoshaUser == null){
            return "login";
        }
        String html = stringRedisTemplate.opsForValue().get(GoodsDetailKeyPrefix.goodsDetail.getPrefix()+goodsId);
        if (html == null) {
            GoodsVo goodsVo = goodsVoService.findGoodsVoByGoodsId(goodsId);
            long remainTime = -1 ;
            int miaoshaStatus = 0;// 秒杀未开始

            if(goodsVo != null){
                //开始时间毫秒数
                long startTime = goodsVo.getStartDate().getTime();
                //结束时间
                long endTime = goodsVo.getEndDate().getTime();
                // 现在时间
                long now = System.currentTimeMillis();
                if(startTime > now){ // 秒杀未开始 开始倒计时
                    miaoshaStatus=0;
                    remainTime = (startTime-now) /1000;
                } else if(endTime < now){ // 秒杀已经结束，倒计时 -1
                    miaoshaStatus=2;
                    remainTime = -1;
                } else{ // 秒杀进行中
                    miaoshaStatus=1;
                    remainTime = 0;
                }
            }

            model.addAttribute("user",miaoshaUser);
            model.addAttribute("goodsVo",goodsVo);
            model.addAttribute("miaoshaStatus",miaoshaStatus);
            model.addAttribute("remainTime",remainTime);
            // 获取模板引擎
            SpringTemplateEngine templateEngine = thymeleafViewResolver.getTemplateEngine();
            SpringWebContext springWebContext = new SpringWebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap(),applicationContext);

            html = templateEngine.process("goodsDetail",springWebContext);
            stringRedisTemplate.opsForValue().set(GoodsDetailKeyPrefix.goodsDetail.getPrefix()+goodsId,html);

        }
        return html;
       // return "goodsDetail";
    }


    @RequestMapping(value="/goodsDetailOld/{goodsId}",method = RequestMethod.GET)
    @NeedLogin
    public String goodsDetailOld(MiaoshaUser miaoshaUser, Model model, @PathVariable("goodsId") long goodsId){
        if(miaoshaUser == null){
            return "login";
        }
        GoodsVo goodsVo = goodsVoService.findGoodsVoByGoodsId(goodsId);
        long remainTime = -1 ;
        int miaoshaStatus = 0;// 秒杀未开始

        if(goodsVo != null){
            //开始时间毫秒数
            long startTime = goodsVo.getStartDate().getTime();
            //结束时间
            long endTime = goodsVo.getEndDate().getTime();
            // 现在时间
            long now = System.currentTimeMillis();
            if(startTime > now){ // 秒杀未开始 开始倒计时
                miaoshaStatus=0;
                remainTime = (startTime-now) /1000;
            } else if(endTime < now){ // 秒杀已经结束，倒计时 -1
                miaoshaStatus=2;
                remainTime = -1;
            } else{ // 秒杀进行中
                miaoshaStatus=1;
                remainTime = 0;
            }
        }

        model.addAttribute("user",miaoshaUser);
        model.addAttribute("goodsVo",goodsVo);
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainTime",remainTime);
        return "goodsDetail";
    }


    @RequestMapping(value = "/goodsList",method = RequestMethod.GET)
    @NeedLogin
    public String goodsList(MiaoshaUser miaoshaUser,Model model){
        if(miaoshaUser == null){
            return "login";
        }
        List<GoodsVo> goodsVoList = goodsVoService.findGoodsVoList();
        model.addAttribute("user",miaoshaUser);
        model.addAttribute("goodsList",goodsVoList);
        return "goodsList";
    }

    /**
     * 获取cookie中的token
     * @param request
     * @return
     */
    public String getCookieToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie:cookies) {
                if ("USER_COOKIE_KEY".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
