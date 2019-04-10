package com.ayuantalking.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
*@author ayuantalking
*@createTime 2019年3月21日下午3:35:29
* ....
*/
@Data
public class MiaoshaUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5455761012264756625L;
	private Long   id;
	private String nickname;
	private String password;
	private String salt;
	private String head;
	private Date   registerDate;
	private Date    lastLoginDate;
	private Integer loginCount;
}
