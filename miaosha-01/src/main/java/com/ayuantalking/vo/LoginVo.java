package com.ayuantalking.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ayuantalking.validator.constraints.IsMobile;

import lombok.Data;

/**
*@author ayuantalking
*@createTime 2019年3月22日上午10:56:04
* ....
*/
@Data
public class LoginVo {

	@NotEmpty
	@Length(min=11,max=11)
	@IsMobile(required=true)
	private String mobile;
	@NotEmpty
	private String password;
	
}
