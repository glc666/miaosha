package com.ayuantalking.config;

import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 11:01
 * @Version 1.0
 */
public class NeedLoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       if(handler instanceof HandlerMethod){
           HandlerMethod handlerMethod = (HandlerMethod) handler;
           NeedLogin needLogoin = handlerMethod.getMethodAnnotation(NeedLogin.class);
           if(needLogoin != null){
               String paramToken = request.getParameter("token");
               String cookieToken = getCookieToken(request);
               String token = cookieToken == null ? paramToken :  cookieToken;
               MiaoshaUser miaoshaUser = miaoshaUserService.getByToken(response, token);
               if (miaoshaUser != null) {
                   return true;
               }else{
                    //  方法声明了@needLogoin 但是没有获取到用户或则用户登录信息已经失效了
                    //   跳转到用户登录页面
                   return false;
               }
           }else{
               return true;
           }

       }
        return true;
    }

    private String getCookieToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if("USER_COOKIE_KEY".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
