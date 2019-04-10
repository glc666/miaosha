package com.ayuantalking.service.impl;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ayuantalking.dao.MiaoshaUserDao;
import com.ayuantalking.exception.GlobalExcepiton;
import com.ayuantalking.redis.MiaoshaUserKey;
import com.ayuantalking.redis.UUIDUtil;
import com.ayuantalking.util.ConstantUtil;
import com.ayuantalking.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ayuantalking.domain.MiaoshaUser;
import com.ayuantalking.result.CodeMsg;
import com.ayuantalking.result.Result;
import com.ayuantalking.service.MiaoshaUserService;
import com.ayuantalking.vo.LoginVo;

/**
*@author ayuantalking
*@createTime 2019年3月22日上午11:02:06
* ....
*/
@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService{

	private static String USER_COOKIE_KEY = "USER_COOKIE_KEY";

	@Autowired
	MiaoshaUserDao miaoshaUserDao;

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public Result<String> doLogin(HttpServletResponse response,LoginVo loginVo) throws Exception {
		MiaoshaUser miaoshaUser = miaoshaUserDao.findMiaoshaUserByMobile(loginVo.getMobile());
		if(miaoshaUser == null) {
			throw new GlobalExcepiton(CodeMsg.ACCOUNT_ERROR);
		}
		//表单提交过来的加密后密码
		String formPassword = loginVo.getPassword();
		//数据库的密码
		String dbPassword = miaoshaUser.getPassword();
		//把表单提交过来的密码加密成数据库的密码
		formPassword = MD5Util.formPasstoDBPass(formPassword,miaoshaUser.getSalt());
		
		if(!dbPassword.equals(formPassword)) {
			throw new GlobalExcepiton(CodeMsg.PASSWORD_ERROR);
		}

		String token = UUIDUtil.uuid();
		saveMiaoshaUserToRedisAndRedis(miaoshaUser,token,response);

		// 把登录成功的用户添加到redis
		//addMiaoshauserToRedis(miaoshaUser);
		// 给用户设置cookie
		//addCookie(response, miaoshaUser);
		
		/*HttpSession session = request.getSession();
		session.setAttribute(ConstantUtil.MS_USER_SESSION_KEY, miaoshaUser);*/
		
		return Result.success(token);
	}

	public MiaoshaUser getByToken(HttpServletResponse response,String token){
		MiaoshaUser miaoshaUser = (MiaoshaUser)redisTemplate.opsForValue().get(MiaoshaUserKey.token.getPrefix()+token);
		if (miaoshaUser != null) {
			saveMiaoshaUserToRedisAndRedis(miaoshaUser,token,response);
		}
		return miaoshaUser;
	}


	/**
	 * 保存秒杀用户到redis和cookie
	 * @param miaoshaUser
	 * @param token
	 * @param response
	 */
	private void saveMiaoshaUserToRedisAndRedis(MiaoshaUser miaoshaUser,String token, HttpServletResponse response) {
		redisTemplate.opsForValue().set(MiaoshaUserKey.token.getPrefix()+token,miaoshaUser,MiaoshaUserKey.token.expredSeconds(),TimeUnit.SECONDS);
		Cookie cookie = new Cookie(USER_COOKIE_KEY,token);
		cookie.setPath("/");
		cookie.setMaxAge(MiaoshaUserKey.token.expredSeconds());
		response.addCookie(cookie);
	}

	public MiaoshaUser findMiaoshauserByKey(String key) {
		return (MiaoshaUser) redisTemplate.opsForValue().get(key);
	}

	/**
	 * 把登录成功的用户添加到redis
	 * @param miaoshaUser
	 */
	private void addMiaoshauserToRedis(MiaoshaUser miaoshaUser) {
		redisTemplate.opsForValue().set(ConstantUtil.MS_USER_SESSION_KEY+":"+miaoshaUser.getId(), miaoshaUser, ConstantUtil.EXPREDSECOND, TimeUnit.SECONDS);
	}
	
	/**
	 * 给用户设置cookie
	 * @param response
	 * @param miaoshaUser
	 */
	private void addCookie(HttpServletResponse response, MiaoshaUser miaoshaUser) {
		Cookie cookie = new Cookie(ConstantUtil.MS_USER_COOKIE_KEY, ConstantUtil.MS_USER_SESSION_KEY+":"+miaoshaUser.getId());
		cookie.setMaxAge(Integer.parseInt(ConstantUtil.EXPREDSECOND+""));
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	@Override
	public Result doLogin(HttpServletRequest request,HttpServletResponse response,LoginVo loginVo) throws Exception {
		MiaoshaUser miaoshaUser = miaoshaUserDao.findMiaoshaUserByMobile(loginVo.getMobile());
		if(miaoshaUser == null) {
			throw new GlobalExcepiton(CodeMsg.ACCOUNT_ERROR);
		}
		//表单提交过来的加密后密码
		String formPassword = loginVo.getPassword();
		//数据库的密码
		String dbPassword = miaoshaUser.getPassword();
		//把表单提交过来的密码加密成数据库的密码
		formPassword = MD5Util.formPasstoDBPass(formPassword,miaoshaUser.getSalt());
		
		if(!dbPassword.equals(formPassword)) {
			throw new GlobalExcepiton(CodeMsg.PASSWORD_ERROR);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(ConstantUtil.MS_USER_SESSION_KEY, miaoshaUser);
		
		return Result.success(CodeMsg.SUCCESS);
	}

	

/*	@Override
	public Result doLogin(LoginVo loginVo) {
		MiaoshaUser miaoshaUser = userDao.findMiaoshaUserByMobile(loginVo.getMobile());
		if(miaoshaUser == null) {
			return Result.fail(CodeMsg.ACCOUNT_ERROR);
		}
		
		String dbPassword = miaoshaUser.getPassword();
		String formPassword = loginVo.getPassword();
		
		if(!dbPassword.equals(formPassword)) {
			return Result.fail(CodeMsg.PASSWORD_ERROR);
		}
		return Result.success(CodeMsg.SUCCESS);
	}
	*/
	
}
