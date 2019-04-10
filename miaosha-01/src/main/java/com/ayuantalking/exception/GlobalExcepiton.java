package com.ayuantalking.exception;

import com.ayuantalking.result.CodeMsg;

/**
*@author ayuantalking
*@createTime 2019年3月22日下午1:35:50
* ....
*/
public class GlobalExcepiton extends Exception {

	private CodeMsg cm;
	
	public GlobalExcepiton() {
		
	}

	public GlobalExcepiton(CodeMsg cm) {
		super(cm.toString());
		this.cm = cm;
	}

	public CodeMsg getCm() {
		return cm;
	}
	
	
}
