package com.ayuantalking.exception;

import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ayuantalking.result.CodeMsg;
import com.ayuantalking.result.Result;

/**
*@author ayuantalking
*@createTime 2019年3月22日下午1:41:12
* ....
*/
/*@ControllerAdvice
@ResponseBody*/
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public Result globalException(Exception e) {
		if(e instanceof GlobalExcepiton) {
			GlobalExcepiton globalExcepiton = (GlobalExcepiton)e;
			return Result.fail(globalExcepiton.getCm());
		} else if(e instanceof BindException) {
			BindException bindException = (BindException)e;
			
			List<ObjectError> allErrors = bindException.getAllErrors();
			
			ObjectError objectError = allErrors.get(0);
			
			objectError.getDefaultMessage();
			
			return Result.fail(CodeMsg.BIND_EXCEPTION.getAllArges(objectError.getDefaultMessage()));
		}
		e.printStackTrace();
		return Result.fail(CodeMsg.SERVICE_ERROR);
	}
}
