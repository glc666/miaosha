package com.ayuantalking.config;

import com.ayuantalking.annotation.NeedLogin;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.service.MiaoshaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 15:27
 * @Version 1.0
 */
@Service
public class NeedLoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NeedLogin needLogin = handlerMethod.getMethodAnnotation(NeedLogin.class);
            if (needLogin != null) {
                MiaoshaUser miaoshaUser = getMiaoshaUser(request, response);
                if (miaoshaUser == null) {
                    response.sendRedirect("/login/to_login");
                    return false;
                }else{
                    LocalUserHandler.setUser(miaoshaUser);
                    return true;
                }
            }
        }
        return true;
    }

    private MiaoshaUser getMiaoshaUser(HttpServletRequest request, HttpServletResponse response) {
        // 从请求参数中获取token 一般是移动端端
        String paramToken  = request.getParameter("token");
        // 从cookie中获取token
        String cookieToken = getCookieToken(request);
        paramToken = paramToken == null ? cookieToken : paramToken;

        return miaoshaUserService.getByToken(response,paramToken);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

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
