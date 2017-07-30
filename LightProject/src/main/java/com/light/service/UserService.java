package com.light.service;

import com.light.pojo.LgUser;

public interface UserService {

	/**
	 * 获取微信个人用户信息
	 * @Title: getUserInfoById 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午8:24:20
	 */
	LgUser getUserInfoById(Integer userId);
}
