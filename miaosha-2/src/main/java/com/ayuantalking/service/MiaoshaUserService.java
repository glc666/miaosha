package com.ayuantalking.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.result.Result;
import com.ayuantalking.vo.LoginVo;

/**
*@author ayuantalking
*@createTime 2019年3月22日上午11:01:22
* ....
*/
public interface MiaoshaUserService {

	Result<String> doLogin(HttpServletResponse response, LoginVo loginVo) throws Exception;
	Result doLogin(HttpServletRequest request, HttpServletResponse response, LoginVo loginVo) throws Exception;

	public MiaoshaUser getByToken(HttpServletResponse response,String token);
	MiaoshaUser findMiaoshauserByKey(String key);

}
