package com.ayuantalking.validator.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
*@author ayuantalking
*@createTime 2019年3月22日上午11:51:01
* ....
*/
@Documented
@Constraint(validatedBy = {IsMobileValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface IsMobile {
	
	boolean required() default true;
	
	String message() default "手机号码格式不正确";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	

}
