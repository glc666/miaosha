package com.ayuantalking.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ayuantalking.domain.MiaoshaUser;

/**
*@author ayuantalking
*@createTime 2019年3月22日上午11:19:02
* ....
*/
@Mapper
public interface MiaoshaUserDao {
	
	@Select(" select * from miaosha_user where mobile =#{mobile} ")
	MiaoshaUser findMiaoshaUserByMobile(@Param(value = "mobile") String mobile);

}
