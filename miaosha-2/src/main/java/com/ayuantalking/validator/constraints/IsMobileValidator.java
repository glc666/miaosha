package com.ayuantalking.validator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ayuantalking.util.ValidatorUtil;
import org.springframework.util.StringUtils;


/**
*@author ayuantalking
*@createTime 2019年3月22日上午11:56:11
* ....
*/
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

	boolean required = true;
	
	@Override
	public void initialize(IsMobile constraintAnnotation) {
		required = constraintAnnotation.required();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {
			return ValidatorUtil.isMobile(value);
		}else {
			if(StringUtils.isEmpty(value)) {
				return true;
			}else {
				return ValidatorUtil.isMobile(value);
			}
		}
	}

}
