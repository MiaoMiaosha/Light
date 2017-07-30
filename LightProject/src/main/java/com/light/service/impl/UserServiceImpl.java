package com.light.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.light.mapper.LgUserMapper;
import com.light.pojo.LgUser;
import com.light.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired LgUserMapper LgUserMapper;
	//获取微信个人用户信息
	@Override
	public LgUser getUserInfoById(Integer userId) {
		LgUser lgUser = LgUserMapper.selectByPrimaryKey(userId); 
		return lgUser;
	}

	
}
