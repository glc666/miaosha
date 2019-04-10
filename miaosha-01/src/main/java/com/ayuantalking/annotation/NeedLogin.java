package com.ayuantalking.annotation;

import java.lang.annotation.*;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 15:23
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLogin {
}
