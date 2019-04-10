package com.ayuantalking.result;

import lombok.Data;

/**
*@author ayuantalking
*@createTime 2019年3月22日上午10:38:15
* ....
*/
@Data
public class CodeMsg {


	private int code;
	private String msg;

	// 5001XX 通用返回
	public static CodeMsg SUCCESS= new CodeMsg(0,"操作成功");
	public static CodeMsg SERVICE_ERROR= new CodeMsg(500100,"服务器异常，请稍后再试");
	public static final CodeMsg BIND_EXCEPTION = new CodeMsg(500101,"参数绑定异常:%s");


	// 5002XX 登录返回
	public static CodeMsg ARGS_ERROR= new CodeMsg(500200,"请求参数非法");
	public static CodeMsg MOBILE_NOT_NULL= new CodeMsg(500201,"手机号码不能为空");
	public static CodeMsg MOBILE_ERROR= new CodeMsg(500201,"手机号码格式不正确");
	public static CodeMsg PASSWORD_NOT_NULL= new CodeMsg(500202,"密码不能为空");
	public static CodeMsg ACCOUNT_ERROR= new CodeMsg(500203,"账号不存在");
	public static CodeMsg PASSWORD_ERROR= new CodeMsg(500202,"密码错误");

	// 5003XX 订单返回
	//public static CodeMsg SUCCESS= new CodeMsg(0,"操作成功");
	public static CodeMsg GOODS_NOT_EXIST = new CodeMsg(500300,"您秒杀的商品不存在");
	public static CodeMsg GOODS_SELL_OUT = new CodeMsg(500301,"哇哦，来晚了，商品已经被抢光了");
	public static CodeMsg REPEAT_SECKILL = new CodeMsg(500302,"不要贪心哦，您已经秒杀到商品了");
	public static CodeMsg QUEUE_UP = new CodeMsg(500303,"排队中");

	public CodeMsg(int code, String message) {
		this.code=code;
		this.msg=message;
	}
	
	public CodeMsg getAllArges(Object... defaultMessage) {
		this.msg = String.format(this.msg, defaultMessage);
		return new CodeMsg(this.code, this.msg);
	}
}
