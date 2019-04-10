package com.ayuantalking.config;

import java.lang.annotation.*;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 10:56
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLogin {
}
