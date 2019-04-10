package com.ayuantalking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ayuantalking.result.Result;
import com.ayuantalking.service.MiaoshaUserService;
import com.ayuantalking.vo.LoginVo;

/**
*@author ayuantalking
*@createTime 2019年3月22日上午10:53:34
* ....
*/
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	MiaoshaUserService userService;
	

	@RequestMapping(value="/to_login")
	public String toLogin(Model model) throws Exception{
		model.addAttribute("name", "jack");
		return "login";
	}

	
	@RequestMapping(value="/do_login")
	@ResponseBody
	public Result<Boolean> doLogin(HttpServletResponse response,@Valid LoginVo loginVo) throws Exception{
		return userService.doLogin(response,loginVo);
		//return userService.doLogin(request,response,loginVo);
	}

	
	/*@RequestMapping(value="/do_login")
	@ResponseBody
	public Result<Boolean> doLogin(@Valid LoginVo loginVo){
		if(loginVo == null) {
			return Result.fail(CodeMsg.ARGS_ERROR);
		}
		if(StringUtils.isEmpty(loginVo.getMobile())) {
			return Result.fail(CodeMsg.MOBILE_ERROR);
		}
		if(StringUtils.isEmpty(loginVo.getPassword())) {
			return Result.fail(CodeMsg.PASSWORD_NOT_NULL);
		}
		
		return userService.doLogin(loginVo);
	}
*/

}
