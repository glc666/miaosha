package com.ayuantalking.result;

import lombok.Data;

/**
*@author ayuantalking
*@createTime 2019年3月22日上午10:32:41
* ....
*/
@Data
public class Result<T> {

	private int code;
	private String msg;
	private T data;

	public static <T> Result success() {
		return new Result<T>();
	}

	public static <T> Result success(T result) {
		return new Result<T>(result);
	}

	public static <T> Result fail(CodeMsg cm) {
		return new Result<T>(cm);
	}

	public Result(T result) {
		this.code=CodeMsg.SUCCESS.getCode();
		this.msg=CodeMsg.SUCCESS.getMsg();
		this.data = result;
	}

	public Result() {
		this.code=CodeMsg.SUCCESS.getCode();
		this.msg=CodeMsg.SUCCESS.getMsg();
	}

	public Result(CodeMsg cm) {
		this.code = cm.getCode();
		this.msg = cm.getMsg();
	}
	
}
