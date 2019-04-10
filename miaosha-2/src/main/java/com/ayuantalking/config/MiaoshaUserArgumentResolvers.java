package com.ayuantalking.config;

import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.service.MiaoshaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/25/0025 23:38
 * @Version 1.0
 */
@Component
@Slf4j
public class MiaoshaUserArgumentResolvers implements HandlerMethodArgumentResolver {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> zclass = methodParameter.getParameterType();
        log.info("======supportsParameter============="+zclass);
        return MiaoshaUser.class == zclass;
    }

    @Override
    public MiaoshaUser resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paremToken = request.getParameter("token");
        String cookieToken = getCookieToken(request);
        String token = paremToken != null ? paremToken : cookieToken;
        MiaoshaUser miaoshaUser = miaoshaUserService.getByToken(response,token);
//        if(miaoshaUser == null){
//            response.sendRedirect("/login/to_login");
//        }
        return miaoshaUser;
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
}
