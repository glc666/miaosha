package com.ayuantalking.config;

import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 11:43
 * @Version 1.0
 */
@Service
public class MiaoshaUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //Class<?> aClass = parameter.getParameterType().getClass();
        Class<?> aClass = parameter.getParameterType();
        System.out.println(aClass);
        return aClass == MiaoshaUser.class;
    }

    @Override
    public MiaoshaUser resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
//
//        // 从请求参数中获取token 一般是移动端端
//        String paramToken  = request.getParameter("token");
//        // 从cookie中获取token
//        String cookieToken = getCookieToken(request);
//        paramToken = paramToken == null ? cookieToken : paramToken;
//        MiaoshaUser miaoshaUser =miaoshaUserService.getByToken(response,paramToken);
        MiaoshaUser miaoshaUser = LocalUserHandler.getUser();
        if (miaoshaUser == null) {
            response.sendRedirect("/login/to_login");
        }

        return miaoshaUser;
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
